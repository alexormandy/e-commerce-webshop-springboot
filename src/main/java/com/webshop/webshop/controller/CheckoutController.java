package com.webshop.webshop.controller;

import com.webshop.webshop.service.implementations.AddToBagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private AddToBagServiceImpl addToBagServiceImpl;

    @Autowired
    public CheckoutController(AddToBagServiceImpl addToBagServiceImpl) {
        this.addToBagServiceImpl = addToBagServiceImpl;
    }

    @GetMapping
    public String getCheckoutPage (Model model,
                                   HttpSession session) {

        model.addAttribute("basket", addToBagServiceImpl.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", addToBagServiceImpl.calculateNumberOfItemsInBag(session));
        model.addAttribute("subTotal", addToBagServiceImpl.calculateSubTotal(session));

        return "checkout";
    }

    @PostMapping("/updateTotals")
    public String updateBasketTotals(Model model, HttpSession session){

        if (addToBagServiceImpl.calculateNumberOfItemsInBag(session) == 0) {
            return "fragments/emptyBasket :: emptyBasket";
        }

        model.addAttribute("basket", addToBagServiceImpl.fetchBasket(session));
        model.addAttribute("totalNumberOfItems", addToBagServiceImpl.calculateNumberOfItemsInBag(session));

        double subTotal = addToBagServiceImpl.calculateSubTotal(session);
        model.addAttribute("subTotal", subTotal);

        double deliveryCharge = 3.50D;
        model.addAttribute("deliveryCharge", deliveryCharge);

        double grandTotal = addToBagServiceImpl.calculateGrandTotal(subTotal, deliveryCharge);
        model.addAttribute("grandTotal", grandTotal);

        return "fragments/basketTotals :: basketTotals";
    }

}
