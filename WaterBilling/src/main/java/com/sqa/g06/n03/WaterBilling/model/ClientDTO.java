package com.sqa.g06.n03.WaterBilling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;

public class ClientDTO {
    @JsonProperty("client_id")
    private String clientId;
    private String address;
    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public ClientDTO(Client client){
        User user = client.getUser();
        clientId = client.getId();
        address = client.getAddress();
        username = user.getUsername();
        firstname = user.getFirstname();
        lastname = user.getLastname();
        phone = user.getPhone();
        email = user.getEmail();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "clientId='" + clientId + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
