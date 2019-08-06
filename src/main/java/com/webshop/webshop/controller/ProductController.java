package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping(value = "/products/all")
    public String getProductsAllPage (Model model) {

        ProductModel product1 = new ProductModel("Rubber Duck", "Yellow Duck", 12D, "http://google.com");
        ProductModel product2 = new ProductModel("Football", "Red Ball", 20D, "http://google.com");

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(product1);
        productModelList.add(product2);

        model.addAttribute("productModelList", productModelList);

        return "productsAll";
    }

    @GetMapping(value = "/products/{id}")
    public String getProductsByIDPage (Model model) {

        ProductModel product1 = new ProductModel("Rubber Duck", "Yellow Duck", 12D, "http://google.com");
        ProductModel product2 = new ProductModel("Football", "Red Ball", 20D, "http://google.com");

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(product1);
        productModelList.add(product2);

        model.addAttribute("productModelList", productModelList);

        return "productsAll";
    }

}
