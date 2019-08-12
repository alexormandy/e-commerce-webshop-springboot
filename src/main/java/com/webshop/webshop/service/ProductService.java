package com.webshop.webshop.service;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ProductModel getSingleProduct(String id){

        Long idLong = Long.parseLong(id);
        return productDAO.getSingleProduct(idLong);
    }

    public List<ProductModel> getAllProducts() {

        return productDAO.findAll();
    }
}
