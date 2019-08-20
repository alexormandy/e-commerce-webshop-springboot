package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.hibernate.mapping.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    public CheckoutService() {
    }

    /**
     * Value is parameter for removing -1 and adding items +1
     * @param value
     * @param session
     * @return
     */
    public int updateShoppingBasketValue(int value, HttpSession session) {

        Integer itemsInBag = (Integer) session.getAttribute("itemsInBag");
        if (itemsInBag != null) {
            itemsInBag += value;
        } else {
            itemsInBag = 1;
        }

        session.setAttribute("itemsInBag", itemsInBag);
        return itemsInBag;
    }

    public void checkIfBagIsEmpty(HttpSession session) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        if (basket == null) {
            List<BagItemModel> newBasket = new ArrayList<>();
            session.setAttribute("basket", newBasket);
        }
    }

    public void addToBasket(BagItemModel bagItemModel,
                            HttpSession session) {

        checkIfBagIsEmpty(session);
        setProductQuantity(session, bagItemModel);
    }

    public void setProductQuantity (HttpSession session, BagItemModel bagItemModel) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        int index = basket.indexOf(bagItemModel.getProductId());
        System.out.println(index);

        basket.add(bagItemModel);

//        if (index == -1 ) {
//            basket.add(bagItemModel);
//        } else {
//            BagItemModel bagItem = basket.get(index);
//            bagItem.setProductQuantity(+1);
//            basket.add(bagItem);
//        }
        session.setAttribute("basket", basket);
    }

    /**
     * Lambda, finding product using its identifier and removing it from Array.
     * @param productIdentifier
     * @param session
     */
    public void removeFromBasket(String productIdentifier,
                                 HttpSession session) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        BagItemModel itemToBeRemoved  = basket
                .stream()
                .filter(item -> item.getProductIdentifier() == Integer.parseInt(productIdentifier))
                .findFirst()
                .orElse(null);

        basket.remove(itemToBeRemoved);
        session.setAttribute("basket", basket);
    }

    public List<BagItemModel> fetchBasket(HttpSession session) {

        return (List<BagItemModel>) session.getAttribute("basket");
    }

    public int calculateNumberOfItemsInBag(HttpSession session) {

        checkIfBagIsEmpty(session);
        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        return basket.size();
    }

    /**
     * Loops through basket array and adds it back to object, adding up items based on .size()
     * @return
     */
    public double calculateSubTotal(HttpSession session) {
        double subtotal = 0;

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            subtotal += bagItem.getProductPrice();
        }
        return subtotal;
    }

    public double calculateGrandTotal(double subTotal, double deliveryCharge) {

        return subTotal + deliveryCharge;
    }

}


