package com.webshop.webshop.controller;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.interfaces.BagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class AddToBagController {

    private BagService bagService;

    public AddToBagController(BagService bagService) {
        this.bagService = bagService;
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

        bagService.addToBagAndSetQuantity(session, bagItemModel);
        session.setAttribute("itemsInBag", bagService.calculateNumberOfItemsInBag(session));

        String itemsInBag = String.valueOf(bagService.calculateNumberOfItemsInBag(session));

        return itemsInBag;
    }

    @ResponseBody
    @PostMapping("/remove")
    public String removeProductFromBasket(@RequestParam String productIdentifier,
                                     @RequestParam int quantity,
                                     HttpSession session){

        bagService.removeFromBasket(productIdentifier, quantity, session);
        session.setAttribute("itemsInBag", bagService.calculateNumberOfItemsInBag(session));

        String itemsInBag = String.valueOf(bagService.calculateNumberOfItemsInBag(session));

        return itemsInBag;
    }

}
