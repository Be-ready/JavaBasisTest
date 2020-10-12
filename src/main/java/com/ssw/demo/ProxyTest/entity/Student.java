package com.ssw.demo.ProxyTest.entity;

/**
 * @author wss
 * @created 2020/10/12 9:19
 * @since 1.0
 */
public class Student {
    private String userName;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
