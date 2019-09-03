package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customerRoles")
public class CustomerRoleModel implements Serializable {

    public CustomerRoleModel(int id, String role) {
        this.roleId = id;
        this.role = role;
    }

    public CustomerRoleModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "role")
    private String role;

}
