package com.example.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private Integer id;
    private String name;

    public Role() {
        super();
    }
    public Role(String name) {
        super();
        this.name = name;
    }

    // getter & setter

    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}