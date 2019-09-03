package com.webshop.webshop.service;

import com.webshop.webshop.dao.CustomerDAO;
import com.webshop.webshop.dao.RoleDAO;
import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.model.CustomerRoleModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public ModelAndView register(CustomerModel customerModel) {

        ModelAndView modelAndView = new ModelAndView();
//        CustomerModel customerModelExists = findUserByUserName(userModel.getUserName());
//
//        if (userModelExists != null) {
//            modelAndView.addObject("message", "User already exists");
//
//        } else {
            saveUser(customerModel);
            modelAndView.addObject("message", "Customer has been registered successfully. Please login.");
            modelAndView.addObject("customerModel", new CustomerModel());
            modelAndView.setViewName("registration");
//        }
        return modelAndView;
    }

    public void saveUser(CustomerModel customerModel) {

        customerModel.setPassword(bCryptPasswordEncoder.encode(customerModel.getPassword()));
        customerModel.setActive(1);
        CustomerRoleModel userRole = roleDAO.findByRole("ADMIN");
        customerModel.setCustomerRoleModels(new HashSet<>(Arrays.asList(userRole)));

        customerDAO.save(customerModel);
    }
}
