package org.example.tests;

import com.hazelcast.map.IMap;
import com.hazelcast.simulator.hz.HazelcastTest;
import com.hazelcast.simulator.test.annotations.Prepare;
import com.hazelcast.simulator.test.annotations.Setup;
import com.hazelcast.simulator.test.annotations.TimeStep;
import org.example.ObjectKind;
import org.example.ObjectSize;
import org.example.Utils;

public class QueryTest extends HazelcastTest {

    public ObjectKind objectKind = ObjectKind.COMPACT;

    public int itemCount = 100_000;

    private IMap<Integer, Object> map;
    private String query;

    @Setup
    public void setUp() {
        map = targetInstance.getMap(name);
        query = String.format("SELECT * FROM %s WHERE stringField = 'metin'", map.getName());
    }

    @Prepare(global = true)
    public void prepare() {
        Utils.fillMap(map, itemCount, objectKind, ObjectSize.SMALL);
        Utils.createMapping(targetInstance, map.getName(), objectKind);
    }

    @TimeStep
    public long query() {
        return targetInstance.getSql().execute(query).updateCount();
    }

}
