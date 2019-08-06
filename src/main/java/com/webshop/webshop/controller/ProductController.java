package com.webshop.webshop.controller;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepo;

    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO, ProductRepository productRepo) {
        this.productDAO = productDAO;
        this.productRepo = productRepo;
    }

    @GetMapping
    public String getProductsAllPage (Model model) {

        List<ProductModel> productModelList = productRepo.findAll();

        model.addAttribute("productModelList", productModelList);

        return "productsAll";
    }

    @GetMapping("/{id}")
    public String getProductsByIDPage(@PathVariable String id, Model model) {

        System.out.println("parameter: " + id);

        Long idLong = Long.parseLong(id);

        ProductModel productModelFind = productDAO.getSingleProduct(idLong);

        List<ProductModel> productModelList = new ArrayList<>();
        productModelList.add(productModelFind);

        model.addAttribute("productModelList", productModelList);

        return "productsById";
    }


}
