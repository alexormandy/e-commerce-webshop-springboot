package com.webshop.webshop.service;

import com.webshop.webshop.dao.CustomerDAO;
import com.webshop.webshop.dao.RoleDAO;
import com.webshop.webshop.model.BagItemModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

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

    private CustomerDAO customerDAO;
    private RoleDAO roleDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {

        customerService = new CustomerService(customerDAO, roleDAO, bCryptPasswordEncoder);
    }

    @Test
    public void givenUserIsLoggedInWhenSessionAttributeIsCalledThenVerifyAttributeIsSet() {

        when(mockPrincipal.getName()).thenReturn("alex");
        customerService.checkIfUserIsLoggedIn(mockPrincipal,mockSession);

        verify(mockSession, times(1)).setAttribute("isLoggedIn", true);
    }

    @Test
    public void givenUserIsNotLoggedInWhenSessionAttributeIsCalledThenVerifyAttributeIsSet() {

        when(mockPrincipal.getName()).thenReturn(null);
        customerService.checkIfUserIsLoggedIn(mockPrincipal,mockSession);

        verify(mockSession, times(1)).setAttribute("isLoggedIn", false);
    }

}
