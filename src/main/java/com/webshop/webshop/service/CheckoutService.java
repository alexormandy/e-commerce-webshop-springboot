package com.webshop.webshop.service;

import com.webshop.webshop.dao.ProductDAO;
import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {

    private DecimalFormat decimalFormat = new DecimalFormat("0.##");

    @Autowired
    public CheckoutService() {
    }

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

        checkBagIsEmpty(session);
        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        return basket.size();
    }

    /**
     * Loops through basket array and adds it back to object, adding up items based on .size()
     * @return
     */
    public String calculateSubTotal(HttpSession session) {
        double subtotal = 0;

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            subtotal += bagItem.getProductPrice();
        }

        return decimalFormat.format(subtotal);
    }

    public boolean sizeChecker(String productSize) {

        if (productSize.equals("Product Options")) {
        }
        return true;
    }

}


