package com.webshop.webshop.dao;

import com.webshop.webshop.model.StockModel;
import com.webshop.webshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    public StockModel getAllProducts(Long id){
//        return stockRepository.findAllById(id).orElse(null);
//    }
}
