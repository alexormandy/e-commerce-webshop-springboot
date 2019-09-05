package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
public class StockModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productSize;

    private String productColour;

    private int productQuantity;

    @ManyToOne
    @JoinColumn
    private ProductModel productModel;

    public StockModel(String productSize, String productColour, int productQuantity) {
        this.productSize = productSize;
        this.productColour = productColour;
        this.productQuantity = productQuantity;
    }

    public StockModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColour() {
        return productColour;
    }

    public void setProductColour(String productColour) {
        this.productColour = productColour;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "id=" + id +
                ", productSize='" + productSize + '\'' +
                ", productColour='" + productColour + '\'' +
                ", productQuantity=" + productQuantity +
                ", productModel=" + productModel +
                '}';
    }
}
