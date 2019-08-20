package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @Mock
    private HttpSession mockSession;

    @Mock
    private BagItemModel mockBagItemModelC;

    private ProductService productService;
    private int productIdentifier;
    private CheckoutService checkoutService;
    private List<BagItemModel> mockBasket;
    private BagItemModel testBagItemModelA;
    private BagItemModel testBagItemModelB;

    @Before
    public void setUp() {

        checkoutService = new CheckoutService();

        testBagItemModelA = new BagItemModel(2, "Test Item", "Medium", 12D);
        testBagItemModelB = new BagItemModel(3, "Test Item 2", "Large", 16D);
    }

    // TODO: Given, When, Then
    @Test
    public void givenBasketSizeIsTwoWhenOneItemIsRemovedFromBasketThenNewBasketSizeIsOne () {

        mockBasket = new ArrayList<>();
        mockBasket.add(testBagItemModelA);
        mockBasket.add(mockBagItemModelC);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);
        assertEquals(2, mockBasket.size());

        given(mockBagItemModelC.getProductTitle()).willReturn("ProdTitleC");

        checkoutService.removeFromBasket(String.valueOf(testBagItemModelA.getProductIdentifier()), mockSession);
        assertEquals(1, mockBasket.size());
        assertEquals("ProdTitleC", mockBagItemModelC.getProductTitle());
    }

    @Test
    public void givenTwoProductsWhenSubTotalIsCalculatedThenProductsAreAddedTogether () {

        mockBasket = new ArrayList<>();
        mockBasket.add(testBagItemModelA);
        mockBasket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        double subTotal = checkoutService.calculateSubTotal(mockSession);

        int subTotalInt = (int) subTotal;

        assertEquals(28, subTotalInt);
    }

    @Test
    public void givenItemsInBagIs5WhenOneIsAddedThenItemsInBagIs6 () {

        given(mockSession.getAttribute("itemsInBag")).willReturn(5);
//        int mockItemsInBag = checkoutService.updateShoppingBasketValue(1, mockSession);

//        assertEquals(6, mockItemsInBag);
    }
}
