package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    public CheckoutService() {
    }

    public boolean checkIfSessionIsEmpty(HttpSession session) {
        boolean empty;

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        if (basket == null || basket.isEmpty()) {
            List<BagItemModel> newBasket = new ArrayList<>();
            session.setAttribute("basket", newBasket);
            empty = true;
        } else empty = false;
        return empty;
    }

    public void addToBasket(BagItemModel bagItemModel,
                            HttpSession session) {

        System.out.println(checkIfSessionIsEmpty(session));
        if (checkIfSessionIsEmpty(session)){

            List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
            basket.add(bagItemModel);
            session.setAttribute("basket", basket);

        } else {
            setProductQuantity(session, bagItemModel);
        }
    }

    public void setProductQuantity(HttpSession session, BagItemModel bagItemToAdd) {

        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        boolean addNewProduct = false;
        boolean duplicateProduct = false;

        Iterator iterator = basket.iterator();
        while (iterator.hasNext()) {
            BagItemModel existingBagItem = (BagItemModel) iterator.next();

            if (existingBagItem.getProductId() == bagItemToAdd.getProductId()
                    && existingBagItem.getProductSize().equals(bagItemToAdd.getProductSize())) {
                int currentQuantity = existingBagItem.getProductQuantity();
                existingBagItem.setProductQuantity(currentQuantity +1);
                duplicateProduct = true;
                addNewProduct = false;
            }
            else {
                if(!duplicateProduct) {
                    addNewProduct = true;
                }
            }
        }

        if(addNewProduct) {
            basket.add(bagItemToAdd);
        }



//            if (basketItem == null) {
//
//            }
//            else if (basketItem.getProductId() == bagItemModel.getProductId()) {
//
//                    int index = basket.indexOf(basketItem);
//                    BagItemModel keep = basket.get(index);
//                    basket.remove(index);
//
//                    System.out.println("Pre: " + keep.getProductQuantity());
//
//                    keep.setProductQuantity(keep.getProductQuantity() + 1);
//
//                    System.out.println("Post: " + keep.getProductQuantity());
//
//                    basket.add(keep);
//            }  else {
//
//                basket.add(bagItemModel);
//                }
                session.setAttribute("basket", basket);
    }


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

        checkIfSessionIsEmpty(session);
        List<BagItemModel> basket = (List<BagItemModel>) session.getAttribute("basket");
        return basket.size();
    }

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


