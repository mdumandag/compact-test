package org.example.tests;

import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import com.hazelcast.simulator.hz.HazelcastTest;
import com.hazelcast.simulator.test.BaseThreadState;
import com.hazelcast.simulator.test.annotations.Setup;
import com.hazelcast.simulator.test.annotations.TimeStep;
import com.hazelcast.simulator.test.annotations.Verify;
import org.example.ObjectKind;
import org.example.ObjectSize;
import org.example.Utils;
import org.example.objects.TestObject;
import org.example.objects.TestObjectFactory;


public class WriteTest extends HazelcastTest {

    public ObjectKind objectKind = ObjectKind.COMPACT;
    public ObjectSize objectSize = ObjectSize.SMALL;

    public int itemCount = 100_000;

    private TestObject value;
    private IMap<Integer, TestObject> map;
    private IExecutorService executorService;

    @Setup
    public void setUp() {
        value = TestObjectFactory.create(objectKind, objectSize);
        map = targetInstance.getMap(name);
        executorService = targetInstance.getExecutorService(name);
    }

    @Verify(global = true)
    public void logEntryStats() {
        Utils.logEntryStats(logger, executorService, map.getName());
    }

    @TimeStep
    public void set(BaseThreadState state) {
        map.set(state.randomInt(itemCount), value);
    }

}
