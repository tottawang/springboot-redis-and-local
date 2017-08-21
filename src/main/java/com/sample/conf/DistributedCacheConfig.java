package com.sample.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnProperty(name = "SPRING_CACHE_TYPE", havingValue = "redis")
public class DistributedCacheConfig extends CachingConfigurerSupport {

  public static final String CACHE_NAME_USERS = "users";

  /**
   * Must use specific name redisTemplate (or use same method name) in order to skip RedisTemplate
   * bean setup by Redis auto configure.
   * 
   * @return
   */
  @Bean(name = "redisTemplate")
  public RedisTemplate<Object, Object> getRedisTemplate(
      JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public KeyGenerator usersKeyGenerator() {
    return (o, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append("users");
      return sb.toString();
    };
  }

  /**
   * Redis cache manager is primary bean, lets assume majority caches use Redis.
   * 
   * @param customizerInvoker
   * @param redisTemplate
   * @return
   */
  @Bean
  @Primary
  public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setUsePrefix(true);
    return cacheManager;
  }
}
