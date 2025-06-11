package com.test.myhomequote.data;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ResultRepository {

    @Value("${MyHomeQuote.setting.repositrory.max-capacity}")
    private Integer MAX_CAPACITY;
    protected ConcurrentHashMap<Long, ConcurrentHashMap<Long, ConcurrentSkipListSet<Long>>> repository = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(Long outerId, Long innerId, Long result) {
        lock.writeLock().lock();
        try {
            repository.computeIfAbsent(outerId, id -> new ConcurrentHashMap<>())
                    .computeIfAbsent(innerId, id -> new ConcurrentSkipListSet<>())
                    .add(result);
            trimToMax(outerId, innerId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public ConcurrentHashMap<Long, ConcurrentSkipListSet<Long>> getById(Long outerId) {
        return repository.getOrDefault(outerId, new ConcurrentHashMap<>());
    }

    protected void trimToMax(Long outerId, Long innerId) {
        if (repository.contains(outerId) && repository.get(outerId).containsKey(innerId)
                && repository.get(outerId).get(innerId).size() > MAX_CAPACITY) {
            var result = repository.get(outerId).get(innerId);
            while (result.size() >= MAX_CAPACITY) {
                result.pollLast();
            }
        }
    }
}
