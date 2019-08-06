package com.webshop.webshop.dao;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO {

    private ProductRepository productRepository;

    @Autowired
    public ProductDAO(ProductRepository productRepository) {
       this.productRepository = productRepository;
    }

    public ProductModel getSingleProduct(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(ProductModel productModel) {
        productRepository.save(productModel);
    }

    public void clearRepository() {
        productRepository.deleteAll();
    }
}
