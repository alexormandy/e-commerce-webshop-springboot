package com.webshop.webshop.repository;

import com.webshop.webshop.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    CustomerModel findByUserName(String userName);

}
