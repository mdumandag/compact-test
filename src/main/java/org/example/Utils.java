package org.example;

import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import com.hazelcast.simulator.worker.loadsupport.Streamer;
import com.hazelcast.simulator.worker.loadsupport.StreamerFactory;
import org.apache.log4j.Logger;
import org.example.objects.Mappings;
import org.example.objects.TestObjectFactory;

import java.util.Map;
import java.util.concurrent.Future;

public final class Utils {

    public static void logEntryStats(Logger logger, IExecutorService executorService, String mapName) {

        long totalEntryCount = 0;
        long totalMemoryCost = 0;

        Map<Member, Future<SerializableTuple<Long, Long>>> allEntryStats
                = executorService.submitToAllMembers(new EntryStatsCollector(mapName));

        for (Future<SerializableTuple<Long, Long>> entryStatsFuture : allEntryStats.values()) {
            try {
                SerializableTuple<Long, Long> entryStats = entryStatsFuture.get();
                long entryCount = entryStats.getE0();
                long memoryCost =  entryStats.getE1();
                totalEntryCount += entryCount;
                totalMemoryCost += memoryCost;
            } catch (Exception ignored) {
                logger.warn("Failed to fetch entry stats.");
            }
        }

        logger.info("Total entry count: " + totalEntryCount);
        logger.info("Total memory cost: " + totalMemoryCost);
    }

    public static void fillMap(IMap<Integer, Object> map, int itemCount, ObjectKind objectKind, ObjectSize objectSize) {
        Streamer<Integer, Object> streamer = StreamerFactory.getInstance(map);

        for(int i = 0; i < itemCount; i++) {
            Object o = TestObjectFactory.createRandomized(objectKind, objectSize);
            streamer.pushEntry(i, o);
        }

        streamer.await();
    }

    public static void createMapping(HazelcastInstance instance, String mapName, ObjectKind objectKind) {
        String createMappingStatement = Mappings.mappingFor(objectKind, mapName);
        instance.getSql().execute(createMappingStatement);
    }

}
