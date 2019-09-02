package com.webshop.webshop.service;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.dao.StockDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private ProductDAO productDAO;
    private StockDAO stockDAO;

    @Autowired
    public ProductService(ProductDAO productDAO, StockDAO stockDAO) {
        this.productDAO = productDAO;
        this.stockDAO = stockDAO;
    }

    public ProductService() {
    }

    public ProductModel getSingleProduct(String id){

        try {
            Long idLong = Long.parseLong(id);
            return productDAO.getSingleProduct(idLong);

        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<ProductModel> getAllProducts() {

        return productDAO.findAll();
    }

    public List getSizeDetails(ProductModel productModel){

        List<StockModel> stock = stockDAO.findAll();
        List sizes = new ArrayList();

        for (StockModel item : stock){
            if (item.getProductModel().getId().equals(productModel.getId())) {

                sizes.add(item.getProductSize());
            }
        }
//        List<String> productSizeList = stock.split("\\s*,\\s*");
        return sizes;
    }

}
