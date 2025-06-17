package com.test.myhomequote.data;

import common.ERepositoryType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ResultRepository {

    @Value("${MyHomeQuote.setting.repositrory.max-capacity}")
    private Integer MAX_CAPACITY;
    protected ConcurrentHashMap<Long, ConcurrentSkipListMap<Long, Long>> repository = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public abstract ERepositoryType getType();

    public void add(Long outerId, Long innerId, Long result) {
        lock.writeLock().lock();
        try {
            repository.computeIfAbsent(outerId, id -> new ConcurrentSkipListMap<>())
                    .put(innerId, result);
            trimToMax(outerId, innerId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public ConcurrentSkipListMap<Long, Long> getById(Long outerId) {
        return repository.getOrDefault(outerId, new ConcurrentSkipListMap<>());
    }

    protected void trimToMax(Long outerId, Long innerId) {
        if (repository.containsKey(outerId) &&
                repository.get(outerId).containsKey(innerId) &&
                repository.get(outerId).size() >= MAX_CAPACITY) {
            ConcurrentSkipListMap<Long, Long> dataByInnerId = repository.get(outerId);
            var trimmedData = new ConcurrentSkipListMap<Long, Long>();
            dataByInnerId.entrySet().stream()
                    .sorted(Map.Entry.<Long, Long>comparingByValue().reversed()
                            .thenComparing(Map.Entry::getKey, Comparator.reverseOrder()))
                    .limit(20)
                    .forEachOrdered(e -> trimmedData.put(e.getKey(), e.getValue()));
            repository.put(outerId, trimmedData);
        }
    }
}
