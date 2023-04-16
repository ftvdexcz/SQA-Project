package com.sqa.g06.n03.WaterBilling.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bill", uniqueConstraints = @UniqueConstraint(columnNames = {"month", "year", "client_id"}))
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "tax", nullable = false)
    private double tax;

    @Column(name = "environment_fee", nullable = false)
    private double environmentFee;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "meter_consum", nullable = false)
    private int meterConsum;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) // foreign key role_id
    private Client client;

    public Bill() {
    }

    public Bill(Boolean status, int month, int year, double tax, double environmentFee, double amount, int meterConsum, Client client) {
        this.status = status;
        this.month = month;
        this.year = year;
        this.tax = tax;
        this.environmentFee = environmentFee;
        this.amount = amount;
        this.meterConsum = meterConsum;
        this.client = client;
    }

    public Bill(Integer id, Boolean status, int month, int year, double tax, double environmentFee, double amount, int meterConsum, Client client) {
        this.id = id;
        this.status = status;
        this.month = month;
        this.year = year;
        this.tax = tax;
        this.environmentFee = environmentFee;
        this.amount = amount;
        this.meterConsum = meterConsum;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getEnvironmentFee() {
        return environmentFee;
    }

    public void setEnvironmentFee(double environmentFee) {
        this.environmentFee = environmentFee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMeterConsum() {
        return meterConsum;
    }

    public void setMeterConsum(int meterConsum) {
        this.meterConsum = meterConsum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", status=" + status +
                ", month=" + month +
                ", year=" + year +
                ", tax=" + tax +
                ", environmentFee=" + environmentFee +
                ", amount=" + amount +
                ", meterConsum=" + meterConsum +
                ", client=" + client +
                '}';
    }
}
