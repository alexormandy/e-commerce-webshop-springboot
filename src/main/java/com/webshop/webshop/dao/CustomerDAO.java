package com.webshop.webshop.dao;

import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDAO {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(CustomerModel customerModel) {
        customerRepository.save(customerModel);
    }

}
