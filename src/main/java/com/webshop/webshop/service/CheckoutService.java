package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.hibernate.mapping.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    @Autowired
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

        return itemsInBagGet;
    }

    public void checkBagIsEmpty (HttpSession session) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        if (basket != null) {

        } else {
            List<BagItemModel> newBasket = new ArrayList<>();
            session.setAttribute("basket", newBasket);
        }
    }

    public void addToBasket(int productId,
                            String productTitle,
                            String productSize,
                            Double productPrice,
                            HttpSession session) {

        checkBagIsEmpty(session);
        BagItemModel bagItemModel = new BagItemModel(productId, productTitle, productSize, productPrice);

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        basket.add(bagItemModel);

        session.setAttribute("basket", basket);
    }

    public void removeFromBasket(String productIdentifier,
                                 HttpSession session) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        System.out.println(basket.size());

        BagItemModel itemToBeRemoved  = basket
                .stream()
                .filter(item -> item.getProductIdentifier() == Integer.parseInt(productIdentifier))
                .findFirst()
                .orElse(null);

        basket.remove(itemToBeRemoved);
        session.setAttribute("basket", basket);
        System.out.println(basket.size());
    }

    public List<BagItemModel> fetchBasket(HttpSession session) {

        return (List<BagItemModel>) session.getAttribute("basket");
    }

    public int calculateNumberOfItemsInBag(HttpSession session) {

        checkBagIsEmpty(session);
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

    }


