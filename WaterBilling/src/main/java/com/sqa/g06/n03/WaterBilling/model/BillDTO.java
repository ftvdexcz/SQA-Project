package com.sqa.g06.n03.WaterBilling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sqa.g06.n03.WaterBilling.config.Utils;
import com.sqa.g06.n03.WaterBilling.entity.Bill;

import java.util.Date;

public class BillDTO {
    private boolean status;
    private int month;
    private int year;
    private double tax;
    private double environment;
    private double amount;

    @JsonProperty("payment_date")
    private String paymentDate;

    @JsonProperty("total_amount")
    private Double totalAmount;
    @JsonProperty("meter_consum")
    private int meterConsum;

    @JsonProperty("client_id")
    private String clientId;

    public BillDTO(Bill bill, Date paymentDate) {
        this.status = bill.getStatus();
        this.month = bill.getMonth();
        this.year = bill.getYear();
        this.tax = bill.getTax();
        this.environment = bill.getEnvironmentFee();
        this.amount = bill.getAmount();
        this.meterConsum = bill.getMeterConsum();
        this.clientId = bill.getClient().getId();
        this.paymentDate = Utils.dateToString(paymentDate);
        this.totalAmount = Utils.roundDouble(this.tax + this.environment + this.amount);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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


    public int getMeterConsum() {
        return meterConsum;
    }

    public void setMeterConsum(int meterConsum) {
        this.meterConsum = meterConsum;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = Utils.dateToString(paymentDate);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "status=" + status +
                ", month=" + month +
                ", year=" + year +
                ", tax=" + tax +
                ", environment=" + environment +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", totalAmount=" + totalAmount +
                ", meterConsum=" + meterConsum +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
