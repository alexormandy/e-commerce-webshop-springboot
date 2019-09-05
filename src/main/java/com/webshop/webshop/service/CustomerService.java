package com.webshop.webshop.service;

import com.webshop.webshop.dao.CustomerDAO;
import com.webshop.webshop.dao.RoleDAO;
import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.model.CustomerRoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, RoleDAO roleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerDAO = customerDAO;
        this.roleDAO = roleDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ModelAndView register(CustomerModel customerModel) {

        ModelAndView modelAndView = new ModelAndView();
        CustomerModel customerModelExists = findUserByUsername(customerModel.getUsername());

        if (customerModelExists != null) {
            modelAndView.addObject("message", "User already exists");

        } else {
            saveUser(customerModel);
            modelAndView.addObject("message", "Customer has been registered successfully. Please login.");
            modelAndView.addObject("customerModel", new CustomerModel());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    public CustomerModel findUserByUsername(String username) {

        return customerDAO.findByUsername(username);
    }

    public void saveUser(CustomerModel customerModel) {

        customerModel.setPassword(bCryptPasswordEncoder.encode(customerModel.getPassword()));
        customerModel.setActive(true);
        CustomerRoleModel userRole = roleDAO.findByRole("CUSTOMER");
        customerModel.setCustomerRoleModels(new HashSet<>(Arrays.asList(userRole)));

        customerDAO.save(customerModel);
    }

    public boolean checkIfUserIsLoggedIn(Principal user, HttpSession session) {

        boolean notLoggedIn = false;

        if (user != null) {
            notLoggedIn = false;
            session.setAttribute("notLoggedIn", notLoggedIn);
        } else {
            notLoggedIn = true;
            session.setAttribute("notLoggedIn", notLoggedIn);
        }
        return notLoggedIn;
    }

}
