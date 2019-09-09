package com.webshop.webshop.controller;

import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String getLoginPage() {

        return "login";
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {

        ModelAndView modelAndView = new ModelAndView();
        CustomerModel customerModel = new CustomerModel();

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
