package com.sqa.g06.n03.WaterBilling.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "id")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    public Admin() {
    }

    public Admin(User user) {
        this.user = user;
    }

    public Admin(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", user=" + user +
                '}';
    }
}
