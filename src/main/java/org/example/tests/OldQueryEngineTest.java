package org.example.tests;

import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.hazelcast.simulator.hz.HazelcastTest;
import com.hazelcast.simulator.test.annotations.Prepare;
import com.hazelcast.simulator.test.annotations.Setup;
import com.hazelcast.simulator.test.annotations.TimeStep;
import org.example.ObjectKind;
import org.example.ObjectSize;
import org.example.Utils;

import java.util.Collection;

public class OldQueryEngineTest extends HazelcastTest {

    public ObjectKind objectKind = ObjectKind.COMPACT;
    public ObjectSize objectSize = ObjectSize.SMALL;

    public int itemCount = 100_000;

    private IMap<Integer, Object> map;
    private Predicate<Integer, Object> predicate;

    @Setup
    public void setUp() {
        map = targetInstance.getMap(name);
        predicate = Predicates.equal("stringField", "metin");
    }

    @Prepare(global = true)
    public void prepare() {
        Utils.fillMap(map, itemCount, objectKind, objectSize);
    }

    @TimeStep
    public Collection<Object> query() {
        return map.values(predicate);
    }

}
