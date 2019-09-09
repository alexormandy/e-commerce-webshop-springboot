package com.webshop.webshop.controller;

import com.webshop.webshop.service.implementations.BagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private BagServiceImpl bagServiceImpl;

    @Autowired
    public CheckoutController(BagServiceImpl bagServiceImpl) {
        this.bagServiceImpl = bagServiceImpl;
    }

    @GetMapping
    public String getCheckoutPage (Model model,
                                   HttpSession session) {

        model.addAttribute("basket", bagServiceImpl.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", bagServiceImpl.calculateNumberOfItemsInBag(session));
        model.addAttribute("subTotal", bagServiceImpl.calculateSubTotal(session));

        return "checkout";
    }

    @PostMapping("/updateTotals")
    public String updateBasketTotals(Model model, HttpSession session){

        if (bagServiceImpl.calculateNumberOfItemsInBag(session) == 0) {
            return "fragments/emptyBasket :: emptyBasket";
        }

        model.addAttribute("basket", bagServiceImpl.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", bagServiceImpl.calculateNumberOfItemsInBag(session));

        double subTotal = bagServiceImpl.calculateSubTotal(session);
        model.addAttribute("subTotal", subTotal);

        double deliveryCharge = 3.50D;
        model.addAttribute("deliveryCharge", deliveryCharge);

        double grandTotal = bagServiceImpl.calculateGrandTotal(subTotal, deliveryCharge);
        model.addAttribute("grandTotal", grandTotal);

        return "fragments/basketTotals :: basketTotals";
    }

}
