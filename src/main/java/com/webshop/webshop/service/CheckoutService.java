package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.hibernate.mapping.Bag;
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

    /**
     * Checks if session attribute exists, if not sets it to 1. Then increments.
     * @param id
     * @param productSize
     * @param session
     * @return
     */
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

    public List<BagItemModel> fetchBasket() {

        return basket;
    }

    public int calculateNumberOfItemsInBag() {

        return basket.size();
    }

    /**
     * Loops through basket array and adds it back to object and adds up items based on .size()
     * @return
     */
    public double calculateSubTotal() {
        double subtotal = 0;

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            subtotal += bagItem.getProductPrice();
        }
        return subtotal;
    }

    }

//    public double calculateGrandTotal() {
//
//
//    }


