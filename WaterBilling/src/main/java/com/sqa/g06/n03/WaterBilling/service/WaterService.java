package com.sqa.g06.n03.WaterBilling.service;

public interface WaterService {
    public double calcAmount(int meterConsume);
    public double calcTax(double amount, int taxRate);

    public double calcEnvironmentFee(double amount, int environmentRate);
}
