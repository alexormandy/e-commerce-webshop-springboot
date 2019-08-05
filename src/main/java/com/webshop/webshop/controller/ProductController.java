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

        ProductModel product1 = new ProductModel("Rubber Duck", 12D, "http://google.com");

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(product1);
        System.out.println(product1.toString());

        model.addAttribute("productModelList", productModelList);

        return "productsAll";
    }

}
