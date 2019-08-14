package com.webshop.webshop.model;

import org.springframework.stereotype.Component;

@Component
public class BagItemModel {

    private int productId;

    private String productTitle;

    private String productSize;

    private Double productPrice;

    public BagItemModel(int productId, String productTitle, String productSize, Double productPrice) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    public BagItemModel() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
