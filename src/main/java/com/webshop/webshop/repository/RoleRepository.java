package com.webshop.webshop.repository;

import com.webshop.webshop.model.CustomerRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<CustomerRoleModel, Integer> {
    CustomerRoleModel findByRole(String role);
}
