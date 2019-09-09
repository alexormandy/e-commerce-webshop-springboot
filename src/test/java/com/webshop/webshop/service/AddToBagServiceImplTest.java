package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.implementations.AddToBagImpl;
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

@RunWith(MockitoJUnitRunner.class)
public class AddToBagImplTest {

    @Mock
    private HttpSession mockSession;

    @Mock
    private BagItemModel mockBagItemModelC;

    private AddToBagImpl addToBagImpl;

    private List<BagItemModel> mockBasket;

    private BagItemModel testBagItemModelA;

    private BagItemModel testBagItemModelB;

    @Before
    public void setUp() {

        addToBagImpl = new AddToBagImpl();

        testBagItemModelA = new BagItemModel(2, "Test Item", "Medium", 12D);
        testBagItemModelB = new BagItemModel(3, "Test Item 2", "Large", 16D);
    }

    @Test
    public void givenBasketSizeIsTwoWhenOneItemIsRemovedFromBasketThenNewBasketSizeIsOne () {

        mockBasket = new ArrayList<>();
        mockBasket.add(testBagItemModelA);
        mockBasket.add(mockBagItemModelC);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        given(mockBagItemModelC.getProductTitle()).willReturn("ProdTitleC");

        addToBagImpl.removeFromBasket(String.valueOf(testBagItemModelA.getProductIdentifier()), 1, mockSession);
        assertEquals(1, mockBasket.size());
        assertEquals("ProdTitleC", mockBagItemModelC.getProductTitle());
    }

    @Test
    public void givenTwoProductsWhenSubTotalIsCalculatedThenProductsAreAddedTogether () {

        mockBasket = new ArrayList<>();
        mockBasket.add(testBagItemModelA);
        mockBasket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        double subTotal = addToBagImpl.calculateSubTotal(mockSession);

        int subTotalInt = (int) subTotal;

        assertEquals(28, subTotalInt);
    }

    @Test
    public void givenItemsInBagIs5WhenOneIsAddedThenItemsInBagIs6 () {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);
        basket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(basket);
        int mockItemsInBag = addToBagImpl.calculateNumberOfItemsInBag(mockSession);

        assertEquals(2, mockItemsInBag);
    }

    @Test
    public void givenProductQuantityIsTwoWhenOneIsRemovedThenQuantityIsOne() {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(basket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        addToBagImpl.removeFromBasket(productIdentifier, 1, mockSession);

        assertEquals(1,testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductQuantityIsTwoWhenTwoIsRemovedThenProductIsRemoved() {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(basket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        addToBagImpl.removeFromBasket(productIdentifier, 2, mockSession);

        assertEquals(-1, basket.indexOf(testBagItemModelA));
    }

    @Test
    public void givenProductQuantityIsZeroWhenZeroIsRemovedThenQuantityShouldRemain() {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(5);

        given(mockSession.getAttribute("basket")).willReturn(basket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        addToBagImpl.removeFromBasket(productIdentifier, 0, mockSession);

        assertEquals(5, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductQuantityIs3When4IsRemovedThenQuantityShouldRemain() {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(3);

        given(mockSession.getAttribute("basket")).willReturn(basket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        addToBagImpl.removeFromBasket(productIdentifier, 4, mockSession);

        assertEquals(3, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductIdentifierDoesNotExistWhenItPassedToMethodRemovedThenQuantityShouldRemain() {

        List<BagItemModel> basket = new ArrayList<>();
        basket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(3);

        given(mockSession.getAttribute("basket")).willReturn(basket);

        String productIdentifier = "8878";
        addToBagImpl.removeFromBasket(productIdentifier, 1, mockSession);

        assertEquals(2, testBagItemModelA.getProductQuantity());
    }

}
