package com.webshop.webshop.repository;

import com.webshop.webshop.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}