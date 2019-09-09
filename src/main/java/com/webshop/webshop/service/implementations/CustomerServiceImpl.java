package com.webshop.webshop.service.implementations;

import com.webshop.webshop.dao.CustomerDAO;
import com.webshop.webshop.dao.RoleDAO;
import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.model.CustomerRoleModel;
import com.webshop.webshop.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO, RoleDAO roleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerDAO = customerDAO;
        this.roleDAO = roleDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ModelAndView register(CustomerModel customerModel) {

        ModelAndView modelAndView = new ModelAndView();
        CustomerModel customerModelFound = findUserByUsername(customerModel.getUserName());

        if (customerModelFound != null) {
            modelAndView.addObject("message", "User already exists");

        } else {
            saveUser(customerModel);
            modelAndView.addObject("message", "Customer has been registered successfully. Please login.");
            modelAndView.addObject("customerModel", new CustomerModel());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @Override
    public CustomerModel findUserByUsername(String userName) {

        return customerDAO.findByUserName(userName);
    }

    @Override
    public void saveUser(CustomerModel customerModel) {

        customerModel.setPassword(bCryptPasswordEncoder.encode(customerModel.getPassword()));
        customerModel.setActive(true);
        CustomerRoleModel userRole = roleDAO.findByRole("CUSTOMER");
        customerModel.setCustomerRoleModels(new HashSet<>(Arrays.asList(userRole)));

        customerDAO.save(customerModel);
    }

}
