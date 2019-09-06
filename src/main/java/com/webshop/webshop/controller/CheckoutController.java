package com.webshop.webshop.controller;

import com.webshop.webshop.service.AddToBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private AddToBagService addToBagService;

    @Autowired
    public CheckoutController(AddToBagService addToBagService) {
        this.addToBagService = addToBagService;
    }

    @GetMapping
    public String getCheckoutPage (Model model,
                                   HttpSession session) {

        model.addAttribute("basket", addToBagService.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", addToBagService.calculateNumberOfItemsInBag(session));
        model.addAttribute("subTotal", addToBagService.calculateSubTotal(session));

        return "checkout";
    }

    @PostMapping("/updateTotals")
    public String updateBasketTotals(Model model, HttpSession session){

        if (addToBagService.calculateNumberOfItemsInBag(session) == 0) {
            return "fragments/emptyBasket :: emptyBasket";
        }

        model.addAttribute("basket", addToBagService.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", addToBagService.calculateNumberOfItemsInBag(session));

        double subTotal = addToBagService.calculateSubTotal(session);
        model.addAttribute("subTotal", subTotal);

        double deliveryCharge = 3.50D;
        model.addAttribute("deliveryCharge", deliveryCharge);

        double grandTotal = addToBagService.calculateGrandTotal(subTotal, deliveryCharge);
        model.addAttribute("grandTotal", grandTotal);

        return "fragments/basketTotals :: basketTotals";
    }

}
