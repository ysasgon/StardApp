package com.example.stardapp.model;

public class Object {
    private String name;
    private String username;
    private Integer type;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Object(String name, String username, Integer type, Integer quantity) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.quantity = quantity;
    }

    public Object() {
    }
}
