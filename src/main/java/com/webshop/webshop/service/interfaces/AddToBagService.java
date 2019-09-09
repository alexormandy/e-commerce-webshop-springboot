package com.webshop.webshop.service.interfaces;

import com.webshop.webshop.model.BagItemModel;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AddToBagInterface {

    public List<BagItemModel> fetchBasket(HttpSession session);

    public boolean checkIfBasketIsEmpty(HttpSession session);

    public void addToBagAndSetQuantity(HttpSession session, BagItemModel bagItemToAdd);

    public void removeFromBasket(String productIdentifier, int quantity, HttpSession session);

    public int calculateNumberOfItemsInBag(HttpSession session);

    public double calculateSubTotal(HttpSession session);

    public double calculateGrandTotal(double subTotal, double deliveryCharge);

}
