package com.komiks.api.domain;

public class Product {

    private String id;
    private String name;
    private Float price;

    public Product() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String toString() {
        return String.format("Product [id=%s, name=%s, price=$%f]", this.id, this.name, this.price);
    }

}
