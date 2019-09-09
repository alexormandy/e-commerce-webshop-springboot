package com.webshop.webshop.service.interfaces;

import com.webshop.webshop.model.CustomerModel;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {

    public ModelAndView register(CustomerModel customerModel);

    public CustomerModel findUserByUsername(String username);

    public void saveUser(CustomerModel customerModel);

}
