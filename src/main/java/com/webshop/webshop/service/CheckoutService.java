package com.webshop.webshop.service;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@Service
public class CheckoutService {

    public int updateShoppingBasketValue(String id,
                                         String productSize,
                                         HttpSession session) {

        Integer itemsInBagGet = (Integer) session.getAttribute("itemsInBag");
        if (itemsInBagGet != null) {
            itemsInBagGet += 1;
        } else {
            itemsInBagGet = 0;
        }

        session.setAttribute("itemsInBag", itemsInBagGet);
        System.out.println(session.getAttribute("itemsInBag"));

        return itemsInBagGet;
    }

}
