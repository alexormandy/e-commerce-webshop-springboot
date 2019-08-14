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

//        List<ProductModel> productModelList = ;
        model.addAttribute("productModelList", productService.getAllProducts());

        return "productsAll";
    }

    @GetMapping("/{id}")
    public String getProductsByIDPage(@PathVariable String id, Model model) {

        ProductModel productModelFind = productService.getSingleProduct(id);

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(productModelFind);
        model.addAttribute("productModelList", productModelList);

        List productSizeList = productService.getSizeDetails(productModelFind);
        model.addAttribute("productSizeList", productSizeList);

        return "productsById";
    }

}
