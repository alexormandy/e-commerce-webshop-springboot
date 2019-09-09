package com.webshop.webshop.controller;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.interfaces.AddToBagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class AddToBagController {

    private AddToBagService addToBagService;

    public AddToBagController(AddToBagService addToBagService) {
        this.addToBagService = addToBagService;
    }

    @ResponseBody
    @PostMapping("/add")
    public String addProductToBasket(@RequestParam(name= "productId") String productId,
                                     @RequestParam(name= "productTitle") String productTitle,
                                     @RequestParam(name= "productSize") String productSize,
                                     @RequestParam(name= "productPrice") String productPrice,
                                     HttpSession session){

        Double productPriceDouble = Double.parseDouble(productPrice);
        int productIdInt = Integer.parseInt(productId);

        BagItemModel bagItemModel = new BagItemModel(productIdInt, productTitle, productSize, productPriceDouble);

        addToBagService.addToBagAndSetQuantity(session, bagItemModel);
        session.setAttribute("itemsInBag", addToBagService.calculateNumberOfItemsInBag(session));

        String itemsInBag = String.valueOf(addToBagService.calculateNumberOfItemsInBag(session));

        return itemsInBag;
    }

    @ResponseBody
    @PostMapping("/remove")
    public String removeProductFromBasket(@RequestParam String productIdentifier,
                                     @RequestParam int quantity,
                                     HttpSession session){

        addToBagService.removeFromBasket(productIdentifier, quantity, session);
        session.setAttribute("itemsInBag", addToBagService.calculateNumberOfItemsInBag(session));

        String itemsInBag = String.valueOf(addToBagService.calculateNumberOfItemsInBag(session));

        return itemsInBag;
    }

}
