package com.tanmoy.redis.service;

import com.tanmoy.redis.model.CacheData;

import java.util.Map;

public interface CacheDataService {

    void save(CacheData cacheData);

    void update(CacheData cacheData);

    void delete(Long id);

    CacheData findById(Long id);

    Map<Long, CacheData> getAll();
}
