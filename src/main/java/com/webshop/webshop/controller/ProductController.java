package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductsAllPage (Model model) {

        model.addAttribute("productsAll", productService.getAllProducts());

        return "productsAll";
    }

    @GetMapping("/{id}")
    public String getProductsByIdPage(@PathVariable String id, Model model) {

        ProductModel productFindById = productService.getSingleProduct(id);
        model.addAttribute("productById", productFindById);

        List productByIdSizes = productService.getSizeDetails(productFindById);
        model.addAttribute("productByIdSizes", productByIdSizes);

        return "productById";
    }

}
