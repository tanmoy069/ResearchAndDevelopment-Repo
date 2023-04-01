package com.tanmoy.redis.service.impl;

import com.tanmoy.redis.model.CacheData;
import com.tanmoy.redis.service.CacheDataService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CacheDataServiceImpl implements CacheDataService {

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, CacheData> hashOperations;

    private final String hashReference = "CacheData";

    @Override
    public void save(CacheData cacheData) {
        hashOperations.putIfAbsent(hashReference, cacheData.getId(), cacheData);
    }

    @Override
    public void saveBulk(Map<Long, CacheData> bulkData) {
        hashOperations.putAll(hashReference, bulkData);
    }

    @Override
    public void update(CacheData cacheData) {
        hashOperations.put(hashReference, cacheData.getId(), cacheData);
    }

    @Override
    public void delete(Long id) {
        hashOperations.delete(hashReference, id);
    }

    @Override
    public CacheData findById(Long id) {
        return hashOperations.get(hashReference, id);
    }

    @Override
    public Map<Long, CacheData> getAll() {
        return hashOperations.entries(hashReference);
    }
}
