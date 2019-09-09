package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.implementations.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    private ProductServiceImpl productServiceImpl;

    @Autowired
    public IndexController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/")
    public String getIndexPage() {

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