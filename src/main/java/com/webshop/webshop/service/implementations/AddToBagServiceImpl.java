package com.webshop.webshop.service.implementations;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.interfaces.AddToBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AddToBagServiceImpl implements AddToBagService {

    final static String BASKET = "basket";

    @Autowired
    public AddToBagServiceImpl() {
    }

    @Override
    public List<BagItemModel> fetchBasket(HttpSession session) {

        return (List<BagItemModel>) session.getAttribute(BASKET);
    }

    @Override
    public boolean checkIfBasketIsEmpty(HttpSession session) {

        boolean empty;
        List<BagItemModel> basket = fetchBasket(session);

        if (basket == null || basket.isEmpty()) {
            List<BagItemModel> newBasket = new ArrayList<>();
            session.setAttribute(BASKET, newBasket);
            empty = true;
        } else {
            empty = false;
        }
        return empty;
    }

    @Override
    public void addToBagAndSetQuantity(HttpSession session, BagItemModel bagItemToAdd) {

        if (checkIfBasketIsEmpty(session)) {

            List<BagItemModel> basket = fetchBasket(session);
            basket.add(bagItemToAdd);
            session.setAttribute(BASKET, basket);

        } else {

            List<BagItemModel> basket = fetchBasket(session);
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
            session.setAttribute(BASKET, basket);
        }
    }

    @Override
    public void removeFromBasket(String productIdentifier,
                                 int quantity,
                                 HttpSession session) {

        List<BagItemModel> basket = fetchBasket(session);

        BagItemModel itemToBeRemoved  = basket
                .stream()
                .filter(item -> item.getProductIdentifier() == Integer.parseInt(productIdentifier))
                .findFirst()
                .orElse(null);

        if (itemToBeRemoved == null) {

        } else if (quantity < itemToBeRemoved.getProductQuantity()) {

            int updatedQuantity = itemToBeRemoved.getProductQuantity() - quantity;
            int indexToUpdate = basket.indexOf(itemToBeRemoved);
            basket.get(indexToUpdate).setProductQuantity(updatedQuantity);

        } else if (quantity == itemToBeRemoved.getProductQuantity()) {
            basket.remove(itemToBeRemoved);
        }

        session.setAttribute(BASKET, basket);
    }

    @Override
    public int calculateNumberOfItemsInBag(HttpSession session) {

        int total = 0;

        checkIfBasketIsEmpty(session);
        List<BagItemModel> basket = fetchBasket(session);

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            total += bagItem.getProductQuantity();
        }
        return total;
    }

    @Override
    public double calculateSubTotal(HttpSession session) {

        double subtotal = 0;

        List<BagItemModel> basket = fetchBasket(session);

        for (int i = 0; i < basket.size(); i++) {
            BagItemModel bagItem = basket.get(i);
            subtotal += bagItem.getProductPrice() * bagItem.getProductQuantity();
        }
        return subtotal;
    }

    @Override
    public double calculateGrandTotal(double subTotal, double deliveryCharge) {

        return subTotal + deliveryCharge;
    }

}


