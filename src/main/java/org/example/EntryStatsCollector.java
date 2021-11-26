package org.example;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.map.LocalMapStats;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class EntryStatsCollector implements Callable<SerializableTuple<Long, Long>>, HazelcastInstanceAware, Serializable {

    private final String mapName;
    private HazelcastInstance instance;

    public EntryStatsCollector(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public SerializableTuple<Long, Long> call() throws Exception {
        LocalMapStats mapStats = instance.getMap(mapName).getLocalMapStats();
        return new SerializableTuple<>(mapStats.getOwnedEntryCount(), mapStats.getOwnedEntryMemoryCost());
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        instance = hazelcastInstance;
    }

}
