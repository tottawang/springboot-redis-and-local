package com.sample.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sample.domain.User;

@Repository
public class SampleRepository {

  @Cacheable(value = "users", keyGenerator = "usersKeyGenerator")
  public List<User> getUsers() {
    System.out.println("getUsers gets called");
    List<User> users = new ArrayList<>();
    users.add(new User(Long.valueOf(0L), "user0"));
    users.add(new User(Long.valueOf(1L), "user1"));
    return users;
  }

  /**
   * Verify query for empty results can be cached as well
   * 
   * @return
   */
  @Cacheable(value = "emptyUsers")
  public List<User> getEmptyUsers() {
    System.out.println("getEmptyUsers gets called");
    List<User> users = new ArrayList<>();
    return users;
  }

  @CacheEvict(value = "users")
  public void evict() {}

  @CacheEvict(value = "emptyUsers")
  public void evictEmptyUsers() {}

}
