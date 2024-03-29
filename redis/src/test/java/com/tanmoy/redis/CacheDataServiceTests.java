package com.tanmoy.redis;

import com.tanmoy.redis.model.CacheData;
import com.tanmoy.redis.service.CacheDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class CacheDataServiceTests {


    private final CacheDataService cacheDataService;

    @Autowired
    CacheDataServiceTests(CacheDataService cacheDataService) {
        this.cacheDataService = cacheDataService;
    }

    @Test
    void saveCacheDataTest() {
        cacheDataService.save(new CacheData(1L, "Test", 10));
        System.out.println(cacheDataService.findById(1L));
    }

    @Test
    void saveBulkCacheDataTest() {
        Map<Long, CacheData> bulkData = new HashMap<>();
        for (long i = 2; i <= 10; i++) {
            bulkData.put(i, new CacheData(i, "Test No : " + i, 10 * (int) i));
        }
        cacheDataService.saveBulk(bulkData);
        Map<Long, CacheData> bulkDataFromCache = cacheDataService.getAll();
        for (CacheData cacheData : bulkDataFromCache.values()) {
            System.out.println(cacheData);
        }
    }

    @Test
    void updateCacheDataTest() {
        cacheDataService.update(new CacheData(1L, "Test No : 1", 10));
        System.out.println(cacheDataService.findById(1L));
    }

    @Test
    void findByIdTest() {
        System.out.println(cacheDataService.findById(1L));
    }

    @Test
    void findAllTest() {
        Map<Long, CacheData> bulkDataFromCache = cacheDataService.getAll();
        for (CacheData cacheData : bulkDataFromCache.values()) {
            System.out.println(cacheData);
        }
    }

    @Test
    void deleteTest() {
        cacheDataService.delete(1L);
        CacheData cacheData = cacheDataService.findById(1L);
        if (cacheData == null) System.out.println("Successfully deleted");
        else System.out.println("Unable to delete data");
    }

}
