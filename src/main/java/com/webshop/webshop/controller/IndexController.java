package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class IndexController {

    private ProductService productService;

    @Autowired
    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getIndexPage () {

        return "index";
    }

    @PostMapping("/search")
    public String getSearchPage(@RequestParam String searchById, Model model) {

        ProductModel productFindById = productService.getSingleProduct(searchById);

        if (productFindById == null) {
            return "fragments/productNotFound :: productNotFound";
        }

        model.addAttribute("productById", productFindById);

//        List productByIdSizes = productService.getSizeDetails(productFindById);
//        model.addAttribute("productByIdSizes", productByIdSizes);

        return "productById";
    }

}