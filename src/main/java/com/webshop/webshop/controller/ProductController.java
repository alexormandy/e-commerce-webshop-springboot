package com.webshop.webshop.controller;

import com.webshop.webshop.model.ProductModel;
import com.webshop.webshop.service.CustomerService;
import com.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public ProductController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getProductsAllPage (Model model, Principal user, HttpSession session) {

        customerService.checkIfUserIsLoggedIn(user, session);

        model.addAttribute("productsAll", productService.getAllProducts());

        return "productsAll";
    }

    @GetMapping("/{id}")
    public String getProductsByIdPage(@PathVariable String id, Model model, Principal user, HttpSession session) {

        customerService.checkIfUserIsLoggedIn(user, session);

        ProductModel productFindById = productService.getSingleProduct(id);
        model.addAttribute("productById", productFindById);

        List productByIdStock = productService.getStockDetails(productFindById);
        model.addAttribute("productByIdStock", productByIdStock);

        return "productById";
    }

}
