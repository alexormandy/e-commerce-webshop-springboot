package com.webshop.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private ArrayList productOptions;

    private String pictureUrl;

    public ProductModel(String name, String description, Double price, ArrayList productOptions, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productOptions = productOptions;
        this.pictureUrl = pictureUrl;
    }

    public ProductModel() {
    }

    public ArrayList getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(ArrayList productOptions) {
        this.productOptions = productOptions;
    }

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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
