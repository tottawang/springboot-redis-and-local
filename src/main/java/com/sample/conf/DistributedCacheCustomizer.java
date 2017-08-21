package com.sample.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
@ConditionalOnProperty(name = "SPRING_CACHE_TYPE", havingValue = "redis")
public class DistributedCacheCustomizer {

  @Bean
  public CacheManagerCustomizer<RedisCacheManager> customize() {
    return cacheManager -> {
      final Map<String, Long> expires = new HashMap<>();
      expires.put("", TimeUnit.MILLISECONDS.toSeconds(60000));
      cacheManager.setExpires(expires);
    };
  }

}
