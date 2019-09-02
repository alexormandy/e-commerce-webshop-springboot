package com.webshop.webshop.repository;

import com.webshop.webshop.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("stockRepository")
public interface StockRepository extends JpaRepository<StockModel, Long> {

}
