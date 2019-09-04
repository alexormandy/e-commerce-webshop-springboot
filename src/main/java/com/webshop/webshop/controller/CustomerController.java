package com.webshop.webshop.controller;

import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.service.CustomerService;
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

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(Principal user, HttpSession session) {

        customerService.checkIfUserIsLoggedIn(user, session);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
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
