package com.sample.resources;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.sample.conf.DistributedCacheConfig;
import com.sample.conf.SampleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {SampleApplication.class, DistributedCacheConfig.class, LocalCacheTestConfig.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SampleResourceTest {

  private SampleResource sampleResource;

  @Autowired
  private LocalCacheRepository localCacheRepository;

  @Mock
  private LocalCacheComponent localCacheComponent;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    sampleResource = new SampleResource();
    ReflectionTestUtils.setField(sampleResource, "localCacheRepository", localCacheRepository);
    ReflectionTestUtils.setField(localCacheRepository, "localCacheComponent", localCacheComponent);
    sampleResource.evictLocalCacheUsers();
  }

  @Test
  public void localCacheExpires() throws InterruptedException {
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(1)).doSomething();
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(1)).doSomething();
    Thread.sleep(5000);
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(2)).doSomething();
  }

  @Test
  public void localCacheEvict() throws InterruptedException {
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(1)).doSomething();
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(1)).doSomething();
    sampleResource.evictLocalCacheUsers();
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(2)).doSomething();
    sampleResource.getLocalCacheUsers();
    verify(localCacheComponent, times(2)).doSomething();
  }

}
