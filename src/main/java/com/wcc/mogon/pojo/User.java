package com.wcc.mogon.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class User implements Serializable {

    private static final long serializableUID = 1L;

    private String id;

    private String name;

    private int age;

    private String password;

    public User(String id, String name, int age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public static long getSerializableUID() {
        return serializableUID;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s,%d,%s)", id, name, age, password);
    }
}
