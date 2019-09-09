package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.implementations.BagServiceImpl;
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
public class BagServiceTest {

    @Mock
    private HttpSession mockSession;

    @Mock
    private BagItemModel mockBagItemModelC;

    private BagServiceImpl bagServiceImpl;

    private List<BagItemModel> mockBasket;

    private BagItemModel testBagItemModelA;

    private BagItemModel testBagItemModelB;

    @Before
    public void setUp() {

        bagServiceImpl = new BagServiceImpl();

        mockBasket = new ArrayList<>();

        testBagItemModelA = new BagItemModel(2, "Test Item", "Medium", 12D);
        testBagItemModelB = new BagItemModel(3, "Test Item 2", "Large", 16D);
    }

    @Test
    public void givenBasketSizeIsTwoWhenOneItemIsRemovedFromBasketThenNewBasketSizeIsOne () {

        mockBasket.add(testBagItemModelA);
        mockBasket.add(mockBagItemModelC);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        given(mockBagItemModelC.getProductTitle()).willReturn("ProdTitleC");

        bagServiceImpl.removeFromBasket(String.valueOf(testBagItemModelA.getProductIdentifier()), 1, mockSession);
        assertEquals(1, mockBasket.size());
        assertEquals("ProdTitleC", mockBagItemModelC.getProductTitle());
    }

    @Test
    public void givenTwoProductsWhenSubTotalIsCalculatedThenProductsAreAddedTogether () {

        mockBasket.add(testBagItemModelA);
        mockBasket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        double subTotal = bagServiceImpl.calculateSubTotal(mockSession);

        int subTotalInt = (int) subTotal;

        assertEquals(28, subTotalInt);
    }

    @Test
    public void givenItemsInBagIs5WhenOneIsAddedThenItemsInBagIs6 () {

        mockBasket.add(testBagItemModelA);
        mockBasket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);
        int mockItemsInBag = bagServiceImpl.calculateNumberOfItemsInBag(mockSession);

        assertEquals(2, mockItemsInBag);
    }

    @Test
    public void givenProductQuantityIsTwoWhenOneIsRemovedThenQuantityIsOne() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        bagServiceImpl.removeFromBasket(productIdentifier, 1, mockSession);

        assertEquals(1,testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductQuantityIsTwoWhenTwoIsRemovedThenProductIsRemoved() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        bagServiceImpl.removeFromBasket(productIdentifier, 2, mockSession);

        assertEquals(-1, mockBasket.indexOf(testBagItemModelA));
    }

    @Test
    public void givenProductQuantityIsZeroWhenZeroIsRemovedThenQuantityShouldRemain() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(5);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        bagServiceImpl.removeFromBasket(productIdentifier, 0, mockSession);

        assertEquals(5, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductQuantityIs3When4IsRemovedThenQuantityShouldRemain() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(3);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        String productIdentifier = String.valueOf(testBagItemModelA.getProductIdentifier());
        bagServiceImpl.removeFromBasket(productIdentifier, 4, mockSession);

        assertEquals(3, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductIdentifierDoesNotExistWhenItPassedToMethodRemovedThenQuantityShouldRemain() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        String productIdentifier = "8878";
        bagServiceImpl.removeFromBasket(productIdentifier, 1, mockSession);

        assertEquals(2, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductQuantityIsTwoWhenOneIsAddedThenProductQuantityIsThree() {

        mockBasket.add(testBagItemModelA);

        testBagItemModelA.setProductQuantity(2);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        bagServiceImpl.addToBagAndSetQuantity(mockSession, testBagItemModelA);

        assertEquals(3, testBagItemModelA.getProductQuantity());
    }

    @Test
    public void givenProductDoesNotExistInBagWhenProductModelIsPassedThenProductQuantityIsOne() {

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        bagServiceImpl.addToBagAndSetQuantity(mockSession, testBagItemModelA);

        assertEquals(1, testBagItemModelA.getProductQuantity());
    }

}
