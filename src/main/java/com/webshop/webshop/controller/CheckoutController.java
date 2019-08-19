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
    public String getCheckoutPage (Model model,
                                   HttpSession session) {

        model.addAttribute("basket", checkoutService.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", checkoutService.calculateNumberOfItemsInBag(session));
        model.addAttribute("subTotal", checkoutService.calculateSubTotal(session));

        return "checkout";
    }

    @ResponseBody
    @PostMapping("/add")
    public String addProductToBasket(@RequestParam(name= "productId") String productId,
                                     @RequestParam(name= "productTitle") String productTitle,
                                     @RequestParam(name= "productSize") String productSize,
                                     @RequestParam(name= "productPrice") String productPrice,
                                     HttpSession session){

        Integer itemsInBag = checkoutService.updateShoppingBasketValue(+1, session);
        String items = String.valueOf(itemsInBag);

        Double productPriceDouble = Double.parseDouble(productPrice);
        int productIdInt = Integer.parseInt(productId);
        checkoutService.addToBasket(productIdInt, productTitle, productSize, productPriceDouble, session);

        return items;
    }

    @ResponseBody
    @PostMapping("/remove")
    public String addProductToBasket(@RequestParam(name= "productIdentifier") String productIdentifier,
                                     HttpSession session){

        System.out.println(productIdentifier);
        checkoutService.removeFromBasket(productIdentifier, session);

        Integer itemsInBag = checkoutService.updateShoppingBasketValue(-1, session);
        String items = String.valueOf(itemsInBag);

        return items;
    }

    @PostMapping("/updateTotals")
    public String updateBasketTotals(Model model, HttpSession session){

        if (checkoutService.calculateNumberOfItemsInBag(session) == 0) {
            return "fragments/fragments :: emptyBasket";
        }

        model.addAttribute("basket", checkoutService.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", checkoutService.calculateNumberOfItemsInBag(session));
        model.addAttribute("subTotal", checkoutService.calculateSubTotal(session));

        return "fragments/fragments :: basketTotals";
    }

}
