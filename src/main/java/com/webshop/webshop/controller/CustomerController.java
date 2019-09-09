package com.webshop.webshop.controller;

import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.service.implementations.CustomerServiceImpl;
import com.webshop.webshop.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String getLoginPage(Principal user, HttpSession session) {

        customerService.checkIfUserIsLoggedIn(user, session);

        return "login";
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage(Principal user, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        CustomerModel customerModel = new CustomerModel();

        customerService.checkIfUserIsLoggedIn(user, session);

        modelAndView.addObject("customerModel", customerModel);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid CustomerModel customerModel) {

        ModelAndView modelAndView = customerService.register(customerModel);

        return modelAndView;
    }

}
