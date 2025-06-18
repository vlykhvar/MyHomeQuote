package com.test.myhomequote.data;

import common.ERepositoryType;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public abstract class ResultRepository {

    @Value("${MyHomeQuote.setting.repositrory.max-capacity}")
    private Integer MAX_CAPACITY;
    protected ConcurrentHashMap<Long, ConcurrentSkipListMap<Long, ConcurrentSkipListSet<Long>>> repository = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public abstract ERepositoryType getType();

    public void add(Long outerId, Long innerId, Long result) {
        repository.computeIfAbsent(outerId, id -> new ConcurrentSkipListMap<>())
                .computeIfAbsent(result, id -> new ConcurrentSkipListSet<>())
                .add(innerId);
        lock.writeLock().lock();
        try {
            trimToMax(outerId, innerId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Optional<ConcurrentSkipListMap<Long, ConcurrentSkipListSet<Long>>> getById(Long outerId) {
        lock.readLock().lock();
        try {
            return Optional.of(repository.get(outerId));
        } finally {
            lock.readLock().unlock();
        }
    }

    protected void trimToMax(Long outerId, Long innerId) {
        ConcurrentSkipListMap<Long, ConcurrentSkipListSet<Long>> innerIdsByResult = repository.get(outerId);
        if (isNull(innerIdsByResult)) {
            return;
        }
        int size = innerIdsByResult.values().stream().mapToInt(ConcurrentSkipListSet::size).sum();
        if (size <= MAX_CAPACITY) {
            return;
        }
        Iterator<Map.Entry<Long, ConcurrentSkipListSet<Long>>> resultIterator = innerIdsByResult.entrySet().iterator();
        while (resultIterator.hasNext() && size > MAX_CAPACITY) {
            Map.Entry<Long, ConcurrentSkipListSet<Long>> entry = resultIterator.next();
            ConcurrentSkipListSet<Long> innerSet = entry.getValue();
            Iterator<Long> innerIterator = innerSet.iterator();
            while (innerIterator.hasNext() && size > MAX_CAPACITY) {
                innerIterator.next();
                innerIterator.remove();
                size--;
            }
            if (innerSet.isEmpty()) {
                resultIterator.remove();
            }
        }
    }
}
