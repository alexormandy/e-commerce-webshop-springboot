package com.webshop.webshop.controller;

import com.webshop.webshop.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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

    @ResponseBody
    @PostMapping("/add")
    public String addProductToBasket(@RequestParam(name= "id") String id,
                                     @RequestParam(name= "productSize") String productSize, HttpSession session) {

        Integer itemsInBagGet = (Integer) session.getAttribute("itemsInBag");
        if (itemsInBagGet != null) {
            itemsInBagGet += 1;
        } else {
            itemsInBagGet = 0;
        }

        session.setAttribute("itemsInBag", itemsInBagGet);
        System.out.println(session.getAttribute("itemsInBag"));

        if (productSize.contains("Product Options")) {
            System.out.println("not valid");
        } else {
            System.out.println("valid");
        }

        return "Success";
    }

}
