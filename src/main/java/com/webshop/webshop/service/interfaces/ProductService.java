package com.webshop.webshop.service.interfaces;

import com.webshop.webshop.model.ProductModel;

import java.util.List;

public interface ProductService {

    public ProductModel getSingleProduct(String id);

    public List<ProductModel> getAllProducts();

    public List<String> getStockDetails(ProductModel productModel);

}
