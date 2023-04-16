package com.sqa.g06.n03.WaterBilling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class CreateBillDTO {
    @Min(value = 1, message = "Month must be between 1-12!")
    @Max(value = 12, message = "Month must be between 1-12!")
    private int month;

    @Min(value = 2000, message = "Invalid year!")
    @Max(value = 2040, message = "Invalid year!")
    private int year;

    @Min(value = 0, message = "Meter consum must be large than 0")
    @JsonProperty("meter_consum")
    private int meterConsum;

    @JsonProperty("client_id")
    private String clientId;

    public CreateBillDTO() {
    }

    public CreateBillDTO(int month, int year, int meterConsum, String clientId) {
        this.month = month;
        this.year = year;
        this.meterConsum = meterConsum;
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return "CreateBillDTO{" +
                "month=" + month +
                ", year=" + year +
                ", meterConsum=" + meterConsum +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
