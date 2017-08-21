package com.sample.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class LocalCacheConfig {

  @Bean(name = "caffeineCacheManager")
  public CaffeineCacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setCaffeine(
        Caffeine.newBuilder().recordStats().expireAfterWrite(60000, TimeUnit.MILLISECONDS));
    return cacheManager;
  }

  @Bean
  public KeyGenerator emptyUsersKeyGenerator() {
    return (o, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append("emptyUsers");
      return sb.toString();
    };
  }

}
