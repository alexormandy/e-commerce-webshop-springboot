package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stock")
public class StockModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productSize;

    private String colour;

    private int productQuantity;

    public StockModel(String productSize, String colour, int productQuantity) {
        this.productSize = productSize;
        this.colour = colour;
        this.productQuantity = productQuantity;
    }

    @OneToMany(mappedBy = "stockModel", cascade = CascadeType.ALL)
    private Set<ProductModel> Product;

}
