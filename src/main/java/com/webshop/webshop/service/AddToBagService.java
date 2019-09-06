package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AddToBagService {

    @Autowired
    public AddToBagService() {
    }

    public boolean checkIfBasketIsEmpty(HttpSession session) {

        boolean empty;
        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        if (basket == null || basket.isEmpty()) {
            List<BagItemModel> newBasket = new ArrayList<>();
            session.setAttribute("basket", newBasket);
            empty = true;
        } else {
            empty = false;
        }
        return empty;
    }

    public void addToBagAndSetQuantity(HttpSession session, BagItemModel bagItemToAdd) {

        if (checkIfBasketIsEmpty(session)) {

            List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
            basket.add(bagItemToAdd);
            session.setAttribute("basket", basket);

        } else {

            List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
            boolean addNewProduct = true;
            boolean duplicateProduct = false;

            Iterator iterator = basket.iterator();
            while (iterator.hasNext()) {
                BagItemModel existingBagItem = (BagItemModel) iterator.next();

                if (existingBagItem.getProductId() == bagItemToAdd.getProductId()
                        && existingBagItem.getProductSize().equals(bagItemToAdd.getProductSize())) {

                    int currentQuantity = existingBagItem.getProductQuantity();
                    existingBagItem.setProductQuantity(currentQuantity + 1);
                    addNewProduct = false;
                    duplicateProduct = true;

                } else {
                    if (!duplicateProduct) {
                        addNewProduct = true;
                    }
                }
            }

            if (addNewProduct) {
                basket.add(bagItemToAdd);
            }
            session.setAttribute("basket", basket);
        }
    }

    public void removeFromBasket(String productIdentifier,
                                 int quantity,
                                 HttpSession session) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        BagItemModel itemToBeRemoved  = basket
                .stream()
                .filter(item -> item.getProductIdentifier() == Integer.parseInt(productIdentifier))
                .findFirst()
                .orElse(null);

        if (quantity < itemToBeRemoved.getProductQuantity()) {

            int updatedQuantity = itemToBeRemoved.getProductQuantity() - quantity;
            int indexToUpdate = basket.indexOf(itemToBeRemoved);
            basket.get(indexToUpdate).setProductQuantity(updatedQuantity);

        } else if (quantity == itemToBeRemoved.getProductQuantity()) {
            basket.remove(itemToBeRemoved);
        }

        session.setAttribute("basket", basket);
    }

    public List<BagItemModel> fetchBasket(HttpSession session) {

        return (List<BagItemModel>) session.getAttribute("basket");
    }

    public int calculateNumberOfItemsInBag(HttpSession session) {

        int total = 0;

        checkIfBasketIsEmpty(session);
        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            total += bagItem.getProductQuantity();
        }
        return total;
    }

    public double calculateSubTotal(HttpSession session) {

        double subtotal = 0;

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            subtotal += bagItem.getProductPrice() * bagItem.getProductQuantity();
        }
        return subtotal;
    }

    public double calculateGrandTotal(double subTotal, double deliveryCharge) {

        return subTotal + deliveryCharge;
    }

}


