package com.webshop.webshop.controller;

import com.webshop.webshop.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public String getCheckoutPage (Model model) {

        return "checkout";
    }

    @PostMapping("/add")
    public String addProductToBasket(@RequestParam(name= "id") String id, @RequestParam(name= "productSize") String productSize) {

        System.out.println(id);
        System.out.println(productSize);

        return "Success";
    }

}
