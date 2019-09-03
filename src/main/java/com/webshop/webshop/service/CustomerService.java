package com.webshop.webshop.service;

import com.webshop.webshop.model.CustomerModel;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CustomerService {

    public ModelAndView register(CustomerModel customerModel) {

        ModelAndView modelAndView = new ModelAndView();
        CustomerModel customerModelExists = findUserByUserName(userModel.getUserName());

//        if (userModelExists != null) {
//            modelAndView.addObject("message", "User already exists");
//
//        } else {
            saveUser(customerModel);
            modelAndView.addObject("message", "User has been registered successfully");
            modelAndView.addObject("userModel", new UserModel());
            modelAndView.setViewName("registration");
//        }
        return modelAndView;
    }

    public void saveUser(CustomerModel customerModel) {

        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userModel.setActive(1);
        RoleModel userRole = roleDAO.findByRole("ADMIN");
        userModel.setRoleModels(new HashSet<>(Arrays.asList(userRole)));

        userDAO.save(userModel);
    }
}
