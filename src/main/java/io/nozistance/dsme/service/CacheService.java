package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    @Scheduled(cron = "${data.update-frequency}")
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .map(cacheManager::getCache)
                .filter(Objects::nonNull)
                .forEach(Cache::clear);
    }
}
