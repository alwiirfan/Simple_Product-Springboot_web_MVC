package com.alwi.irfani.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String code;

    @Column(length = 150, nullable = false)
    private String name;

    private double price;

    public Product() {
    }

    public Product(Long id, String code, String name, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
