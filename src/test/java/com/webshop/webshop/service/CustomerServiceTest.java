package com.webshop.webshop.service;

import com.webshop.webshop.dao.CustomerDAO;
import com.webshop.webshop.dao.RoleDAO;
import com.webshop.webshop.model.BagItemModel;
import com.webshop.webshop.service.implementations.CustomerServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerServiceImpl customerServiceImpl;

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

        customerServiceImpl = new CustomerServiceImpl(customerDAO, roleDAO, bCryptPasswordEncoder);
    }


}
