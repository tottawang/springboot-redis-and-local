package com.sample.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sample.domain.User;

@Repository
@CacheConfig(cacheManager = "caffeineCacheManager", cacheNames = "emptyUsers",
    keyGenerator = "emptyUsersKeyGenerator")
public class LocalCacheRepository {

  @Autowired
  private LocalCacheComponent localCacheComponent;

  /**
   * Verify query for empty results can be cached as well
   * 
   * @return
   */
  @Cacheable
  public List<User> getEmptyUsers() {
    localCacheComponent.doSomething();
    List<User> users = new ArrayList<>();
    return users;
  }

  @CacheEvict(allEntries = true)
  public void evictEmptyUsers() {}


}
