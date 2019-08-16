package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private int productIdentifier;
    private MockHttpSession session;


    @Before
    public void setUp() {
        checkoutService = new CheckoutService();
        session = new MockHttpSession();

        checkoutService.addToBasket(2, "Test Item", "Medium", 12D, session);
        checkoutService.addToBasket(3, "Test Item 2", "Large", 16D, session);

        List<BagItemModel> mockBasket = (List<BagItemModel>) session.getAttribute("basket");
        productIdentifier = mockBasket.get(0).getProductIdentifier();

    }

    // TODO: Given, When, Then
    @Test
    public void givenBasketSizeIsTwoWhenOneItemIsRemovedFromBasketThenNewBasketSizeIsOne () {

        List<BagItemModel> mockBasket = (List<BagItemModel>) session.getAttribute("basket");
        assertEquals(2, mockBasket.size());

        checkoutService.removeFromBasket(String.valueOf(productIdentifier), session);

        mockBasket = (List<BagItemModel>) session.getAttribute("basket");
        assertEquals(1, mockBasket.size());
    }

    @Test
    public void givenProductIdIsTwoWhenProductIdTwoIsRemovedThenPositionZeroIsNowProductIdThree() {

        List<BagItemModel> mockBasket = (List<BagItemModel>) session.getAttribute("basket");

        assertEquals(2, mockBasket.get(0).getProductId());
        checkoutService.removeFromBasket(String.valueOf(productIdentifier), session);
        assertEquals(3, mockBasket.get(0).getProductId());
    }

}
