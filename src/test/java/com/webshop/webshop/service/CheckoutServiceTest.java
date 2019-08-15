package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private int productIdentifier;

    @Before
    public void setUp() {
        checkoutService = new CheckoutService();

        checkoutService.addToBasket(2, "Test Item", "Medium", 12D);
        checkoutService.addToBasket(3, "Test Item 2", "Large", 16D);

        productIdentifier = checkoutService.getBasket().get(0).getProductIdentifier();
    }

    @Test
    public void test1 () {

        assertEquals(2, checkoutService.getBasket().size());

        checkoutService.removeFromBasket(String.valueOf(productIdentifier));
        assertEquals(1, checkoutService.getBasket().size());
    }

    @Test
    public void test2() {

    assertEquals(2, checkoutService.getBasket().get(0).getProductId());
    checkoutService.removeFromBasket(String.valueOf(productIdentifier));
    assertEquals(3, checkoutService.getBasket().get(0).getProductId());

    }

}
