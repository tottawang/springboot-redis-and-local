package com.sample.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sample.domain.User;

@Repository
@CacheConfig(cacheNames = "users", keyGenerator = "usersKeyGenerator")
public class DistributedCacheRepository {

  @Cacheable
  public List<User> getUsers() {
    System.out.println("getUsers gets called");
    List<User> users = new ArrayList<>();
    users.add(new User(Long.valueOf(0L), "user0"));
    users.add(new User(Long.valueOf(1L), "user1"));
    return users;
  }

  @CacheEvict
  public void evict() {}

}
