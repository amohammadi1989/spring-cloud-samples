package com.main.model;
/**
 * Created By: Ali Mohammadi
 * Date: 11 Jan, 2022
 */
public class Result {
  private String id;
  private String name;
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "Result{" +
    "id='" + id + '\'' +
    ", name='" + name + '\'' +
    '}';
  }
}