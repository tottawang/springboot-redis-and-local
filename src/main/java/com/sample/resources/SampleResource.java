package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Path("/api")
@Produces("text/plain")
public class SampleResource {

  @Autowired
  private SampleRepository sampleRepository;

  @Autowired(required = false)
  private RedisTemplate<Object, Object> redisTemplate;

  @Autowired(required = false)
  private RedisCacheManager redisCacheManager;

  @GET
  @Path("/get-users")
  public String getUsers() {
    return sampleRepository.getUsers().toString();
  }

  @GET
  @Path("/get-empty-users")
  @Produces("text/plain")
  public String getEmptyUsers() {
    return sampleRepository.getEmptyUsers().toString();
  }

  @POST
  @Path("/evict")
  public void clearCache() {
    sampleRepository.evict();
    sampleRepository.evictEmptyUsers();
  }

  @GET
  @Path("/caches/{cacheKey}")
  public String getCache(@PathParam("cacheKey") String cacheKey) {
    ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
    return valueOperations.get(cacheKey).toString();
  }

  @GET
  @Path("/init-caches")
  public String testInitCaches() {
    redisCacheManager.initializeCaches();
    return "";
  }

}
