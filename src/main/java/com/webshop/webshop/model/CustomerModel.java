package com.webshop.webshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomerModel implements Serializable {

    private Random randomNumber;

    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String firstLineAddress;

    private String secondLineAddress;

    private String city;

    private String postcode;

    private int telephoneNumber;

    private boolean active;

    public CustomerModel(String userName,
                         String password,
                         String firstName,
                         String lastName,
                         String email,
                         String firstLineAddress,
                         String secondLineAddress,
                         String city,
                         String postcode,
                         int telephoneNumber,
                         boolean active) {
        randomNumber = new Random();
        this.customerId = randomNumber.nextInt(999);
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.firstLineAddress = firstLineAddress;
        this.secondLineAddress = secondLineAddress;
        this.city = city;
        this.postcode = postcode;
        this.telephoneNumber = telephoneNumber;
        this.active = active;
    }

    public CustomerModel() {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_roles_id", joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<CustomerRoleModel> customerRoleModels;

    public Random getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(Random randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstLineAddress() {
        return firstLineAddress;
    }

    public void setFirstLineAddress(String firstLineAddress) {
        this.firstLineAddress = firstLineAddress;
    }

    public String getSecondLineAddress() {
        return secondLineAddress;
    }

    public void setSecondLineAddress(String secondLineAddress) {
        this.secondLineAddress = secondLineAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<CustomerRoleModel> getCustomerRoleModels() {
        return customerRoleModels;
    }

    public void setCustomerRoleModels(Set<CustomerRoleModel> customerRoleModels) {
        this.customerRoleModels = customerRoleModels;
    }

}
