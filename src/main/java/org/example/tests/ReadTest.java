package org.example.tests;

import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import com.hazelcast.simulator.hz.HazelcastTest;
import com.hazelcast.simulator.test.BaseThreadState;
import com.hazelcast.simulator.test.annotations.Prepare;
import com.hazelcast.simulator.test.annotations.Setup;
import com.hazelcast.simulator.test.annotations.TimeStep;
import com.hazelcast.simulator.test.annotations.Verify;
import org.example.ObjectKind;
import org.example.ObjectSize;
import org.example.Utils;
import org.example.objects.TestObject;


public class ReadTest extends HazelcastTest {

    public ObjectKind objectKind = ObjectKind.COMPACT;
    public ObjectSize objectSize = ObjectSize.SMALL;

    public int itemCount = 100_000;

    private IMap<Integer, TestObject> map;
    private IExecutorService executorService;

    @Setup
    public void setUp() {
        map = targetInstance.getMap(name);
        executorService = targetInstance.getExecutorService(name);
    }

    @Prepare(global = true)
    public void prepare() {
        Utils.fillMap(map, itemCount, objectKind, objectSize);
    }

    @Verify(global = true)
    public void logEntryStats() {
        Utils.logEntryStats(logger, executorService, map.getName());
    }

    @TimeStep
    public TestObject get(BaseThreadState state) {
        return map.get(state.randomInt(itemCount));
    }

}
