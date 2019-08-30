package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String productSizes;

    private String pictureUrl;

    public ProductModel(String name, String description, Double price, String productSizes, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productSizes = productSizes;
        this.pictureUrl = pictureUrl;
    }

    public ProductModel() {
    }

    @ManyToOne
    @JoinColumn
    private StockModel stockModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(String productSizes) {
        this.productSizes = productSizes;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
