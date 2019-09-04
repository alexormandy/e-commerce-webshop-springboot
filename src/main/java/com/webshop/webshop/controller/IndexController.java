package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.CustomerService;
import com.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public IndexController(ProductService productService, CustomerService customerService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getIndexPage(Principal user, HttpSession session) {

        customerService.checkIfUserIsLoggedIn(user, session);

        return "index";
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