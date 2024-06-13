package com.online.ecommerce.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    int id;
    @Column(name = "Product_Name")
    String productName;
    @Column(name = "Product_Price")
    Double price;

    @Column(name = "Product_Code",unique = true)
    int productCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public Product() {
    }

    public Product(int id, String productName, Double price, int productCode) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productCode = productCode;
    }
}
