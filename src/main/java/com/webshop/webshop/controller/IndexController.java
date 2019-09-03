package com.webshop.webshop.controller;

import com.webshop.webshop.model.CustomerModel;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.CustomerService;
import com.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public IndexController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String getIndexPage() {

        return "index";
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

    @PostMapping("/search")
    public String getSearchPage(@RequestParam String searchById, Model model) {

        ProductModel productFindById = productService.getSingleProduct(searchById);

        if (productFindById == null) {
            return "fragments/productNotFound :: productNotFound";
        }

        model.addAttribute("productById", productFindById);

        return "productById";
    }

}