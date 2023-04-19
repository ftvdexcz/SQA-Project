package com.sqa.g06.n03.WaterBilling.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PaymentDTO {
    @JsonProperty("client_id")
    private String clientId;

    private String username;

    private int year;
    private int month;
    @JsonProperty("meter_consum")
    private int meterConsum;

    private double tax;

    private double environment;

    private double amount;
    @JsonProperty("total_amount")
    private double totalAmount;

    @JsonProperty(value = "payment_date", access = JsonProperty.Access.READ_ONLY)
    private Date paymentDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMeterConsum() {
        return meterConsum;
    }

    public void setMeterConsum(int meterConsum) {
        this.meterConsum = meterConsum;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getEnvironment() {
        return environment;
    }

    public void setEnvironment(double environment) {
        this.environment = environment;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PaymentDTO(String clientId, String username, int year, int month, int meterConsum, double tax, double environment, double amount, double totalAmount, Date paymentDate) {
        this.clientId = clientId;
        this.username = username;
        this.year = year;
        this.month = month;
        this.meterConsum = meterConsum;
        this.tax = tax;
        this.environment = environment;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "clientId='" + clientId + '\'' +
                ", username='" + username + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", meterConsum=" + meterConsum +
                ", tax=" + tax +
                ", environment=" + environment +
                ", amount=" + amount +
                ", totalAmount=" + totalAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
