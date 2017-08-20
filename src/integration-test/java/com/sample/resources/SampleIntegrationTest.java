package com.sample.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.conf.SampleApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {SampleApplication.class})
public class SampleIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void exampleWebTest() {
    // String body = this.restTemplate.getForObject("/redis-cache", String.class);
    // assertThat(body).isEqualTo("test");
  }

}
