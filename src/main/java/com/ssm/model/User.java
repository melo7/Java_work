package com.ssm.model;
/**
* @Description: 用户表
*/
public class User {
  private Integer id;

  private String userName;

  private String password;

  private Integer age;

  public Integer getId() {
      return id;
  }

  public void setId(Integer id) {
      this.id = id;
  }

  public String getUserName() {
      return userName;
  }

  public void setUserName(String userName) {
      this.userName = userName == null ? null : userName.trim();
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password == null ? null : password.trim();
  }

  public Integer getAge() {
      return age;
  }

  public void setAge(Integer age) {
      this.age = age;
  }

@Override
public String toString() {
	return "User [id=" + id + ", userName=" + userName + ", password="
			+ password + ", age=" + age + ", getId()=" + getId()
			+ ", getUserName()=" + getUserName() + ", getPassword()="
			+ getPassword() + ", getAge()=" + getAge() + ", getClass()="
			+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
			+ super.toString() + "]";
}
  
}