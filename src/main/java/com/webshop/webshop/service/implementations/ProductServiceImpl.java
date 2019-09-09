package com.webshop.webshop.service.implementations;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.dao.StockDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import com.webshop.webshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private StockDAO stockDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, StockDAO stockDAO) {
        this.productDAO = productDAO;
        this.stockDAO = stockDAO;
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

    public List<String> getStockDetails(ProductModel productModel){

        List<StockModel> stock = stockDAO.findAll();
        List stockList = new ArrayList();

        for (StockModel item : stock){
            if (item.getProductModel().getId().equals(productModel.getId())) {

                String entry = String.format(item.getProductColour(), item.getProductSize(), "(",item.getProductQuantity(), "in Stock.", ")");
                stockList.add(entry);
            }
        }
        return stockList;
    }

}
