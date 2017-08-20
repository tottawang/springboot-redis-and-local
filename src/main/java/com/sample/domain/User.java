package com.sample.domain;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long userId;
  private String userName;

  public User(Long userId, String userName) {
    this.userId = userId;
    this.userName = userName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", userName=" + userName + "]";
  }

}
