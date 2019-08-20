package com.webshop.webshop.model;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class BagItemModel {

    Random randomNumber;

    private int productIdentifier;

    private int productId;

    private String productTitle;

    private String productSize;

    private int productQuantity;

    private Double productPrice;

    public BagItemModel(int productId, String productTitle, String productSize, Double productPrice) {
        randomNumber = new Random();
        this.productIdentifier = randomNumber.nextInt(999);
        this.productId = productId;
        this.productTitle = productTitle;
        this.productSize = productSize;
        this.productPrice = productPrice;
        productQuantity = 1;
    }

    public BagItemModel() {
    }

    public int getProductIdentifier() {
        return productIdentifier;
    }

    public void getProductIdentifier(int productIdentifier) {
        this.productIdentifier = productIdentifier;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
