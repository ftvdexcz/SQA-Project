package com.sqa.g06.n03.WaterBilling.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Client() {
    }

    public Client(String address, User user) {
        this.address = address;
        this.user = user;
    }

    public Client(String id, String address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
