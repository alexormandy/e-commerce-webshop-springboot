package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }
}
