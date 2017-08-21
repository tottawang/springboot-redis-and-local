package com.sample.resources;

import org.springframework.stereotype.Component;

@Component
public class LocalCacheComponent {

  public void doSomething() {
    System.out.println("local cache component gets called");
  }

}
