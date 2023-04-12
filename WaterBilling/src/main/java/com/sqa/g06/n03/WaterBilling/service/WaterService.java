package com.sqa.g06.n03.WaterBilling.service;

public interface WaterService {
    public double calcAmount(int meterConsume);
    public double calcTax(double amount);

    public double calcEnvironmentFee(double amount);
}
