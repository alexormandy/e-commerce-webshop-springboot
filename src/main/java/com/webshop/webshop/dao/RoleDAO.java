package com.webshop.webshop.dao;

import com.webshop.webshop.model.CustomerRoleModel;
import com.webshop.webshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO {

    RoleRepository roleRepository;

    @Autowired
    public RoleDAO(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public CustomerRoleModel findByRole(String role){
        return roleRepository.findByRole(role);
    }
}
