package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    private BagItemModel bagItemModel;

    List<BagItemModel> basket;

    @Autowired
    public CheckoutService(BagItemModel bagItemModel) {
        this.bagItemModel = bagItemModel;
        basket = new ArrayList<>();
    }

    public CheckoutService() {
    }

    public int updateShoppingBasketValue(String id,
                                         String productSize,
                                         HttpSession session) {

        Integer itemsInBagGet = (Integer) session.getAttribute("itemsInBag");
        if (itemsInBagGet != null) {
            itemsInBagGet += 1;
        } else {
            itemsInBagGet = 1;
        }

        session.setAttribute("itemsInBag", itemsInBagGet);
        System.out.println(session.getAttribute("itemsInBag"));

        return itemsInBagGet;
    }

    public void addToBasket(int productId,String productTitle, String productSize, Double productPrice) {

        BagItemModel bagItemModel = new BagItemModel(productId, productTitle, productSize, productPrice);
        basket.add(bagItemModel);

    }

    public List<BagItemModel> fetchCheckout() {

        return basket;
    }

    public int calculateNumberOfItemsInBag() {

        return basket.size();
    }

//    public double calculateSubTotal() {
//        double subtotal = 0;
//
//        for (int i = 0; i < basket.size(); i++) {
//            subtotal += basket.get(i);
//        }
//        return subtotal;
//    }

    }

//    public double calculateGrandTotal() {
//
//
//    }

}
