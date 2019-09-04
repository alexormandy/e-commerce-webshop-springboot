package com.webshop.webshop.service;

import com.webshop.webshop.model.BagItemModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private HttpSession mockSession;

    @Mock
    private Principal mockPrincipal;

    private List<BagItemModel> mockBasket;
    private BagItemModel testBagItemModelA;
    private BagItemModel testBagItemModelB;

    @Before
    public void setUp() {

        testBagItemModelA = new BagItemModel(2, "Test Item", "Medium", 12D);
        testBagItemModelB = new BagItemModel(3, "Test Item 2", "Large", 16D);

    }

    @Test
    public void givenUserIsLoggedInWhenSessionAttributeIsCalledThenItReturnsFalse() {

        mockBasket = new ArrayList<>();
        mockBasket.add(testBagItemModelA);
        mockBasket.add(testBagItemModelB);

        given(mockSession.getAttribute("basket")).willReturn(mockBasket);

        boolean notLoggedIn = customerService.checkIfUserIsLoggedIn(mockPrincipal,mockSession);

        assertTrue(notLoggedIn);
    }

}
