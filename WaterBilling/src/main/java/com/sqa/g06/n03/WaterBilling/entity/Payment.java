package com.sqa.g06.n03.WaterBilling.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public Payment() {
    }

    public Payment(int id, Date paymentDate, double totalAmount, Bill bill) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.bill = bill;
    }

    public Payment(Date paymentDate, double totalAmount, Bill bill) {
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", totalAmount=" + totalAmount +
                ", bill=" + bill +
                '}';
    }
}
