package com.sqa.g06.n03.WaterBilling.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PaymentDTO {
    @JsonProperty("total_amount")
    private double totalAmount;

    @JsonProperty("payment_date")
    private Date paymentDate;

    public PaymentDTO(double totalAmount, Date paymentDate) {
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
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

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "totalAmount=" + totalAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
