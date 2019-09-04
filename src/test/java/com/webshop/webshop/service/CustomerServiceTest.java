package com.webshop.webshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private HttpSession mockSession;

    @Mock
    private Principal mockPrincipal;

    @Before
    public void setUp() {

    }

    @Test
    public void givenUserIsLoggedInWhenSessionAttributeIsCalledThenItReturnsFalse() {

        boolean notLoggedIn = customerService.checkIfUserIsLoggedIn(mockPrincipal,mockSession);

        assertTrue(notLoggedIn);
    }

}
