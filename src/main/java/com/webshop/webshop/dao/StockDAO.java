package com.webshop.webshop.dao;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.model.StockModel;
import com.webshop.webshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StockDAO {

    private StockRepository stockRepository;

    @Autowired
    public StockDAO(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void saveStock(StockModel stockModel) {
        stockRepository.save(stockModel);
    }

    public void clearRepository() {
        stockRepository.deleteAll();
    }

    public StockModel getSingleProduct(Long id){
        return stockRepository.findById(id).orElse(null);
    }

    public List<StockModel> findAll() {
        return stockRepository.findAll();
    }
}
