package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api")
@Produces("text/plain")
public class SampleResource {

  @Autowired
  private LocalCacheRepository localCacheRepository;

  @Autowired
  private DistributedCacheRepository remoteCacheRepository;

  @GET
  @Path("/dcache/users")
  public String getDistributedCacheUsers() {
    return remoteCacheRepository.getUsers().toString();
  }

  @GET
  @Path("/dcache/evict")
  @Produces("text/plain")
  public void evictDistributedCacheUsers() {
    remoteCacheRepository.evict();
  }

  @GET
  @Path("/lcache/users")
  @Produces("text/plain")
  public String getLocalCacheUsers() {
    return localCacheRepository.getEmptyUsers().toString();
  }

  @GET
  @Path("/lcache/evict")
  @Produces("text/plain")
  public void evictLocalCacheUsers() {
    localCacheRepository.evictEmptyUsers();
  }
}
