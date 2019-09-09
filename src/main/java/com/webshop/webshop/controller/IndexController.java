package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.implementations.CustomerServiceImpl;
import com.webshop.webshop.service.implementations.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    private ProductServiceImpl productServiceImpl;
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    public IndexController(ProductServiceImpl productServiceImpl, CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/")
    public String getIndexPage(Principal user, HttpSession session) {

        customerServiceImpl.checkIfUserIsLoggedIn(user, session);

        return "index";
    }

    @PostMapping("/search")
    public String getSearchPage(@RequestParam String searchById, Model model) {

        ProductModel productFindById = productServiceImpl.getSingleProduct(searchById);

        if (productFindById == null) {
            return "fragments/productNotFound :: productNotFound";
        }

        model.addAttribute("productById", productFindById);

        return "productById";
    }

}