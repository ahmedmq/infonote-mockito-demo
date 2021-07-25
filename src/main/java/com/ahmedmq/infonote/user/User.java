package com.ahmedmq.infonote.user;

public class User {
  String name;
  String role;

  public User(String name, String role) {
    this.name = name;
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public String getRole() {
    return role;
  }
}
