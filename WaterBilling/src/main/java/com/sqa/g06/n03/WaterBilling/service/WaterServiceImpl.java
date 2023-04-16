package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.config.Config;
import com.sqa.g06.n03.WaterBilling.config.ConfigSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaterServiceImpl implements WaterService{
    @Autowired
    private ConfigSingleton config;

    @Override
    public double calcAmount(int meterConsume) {
        double s = 0;
        Config c = config.getConfig();
        double l1 = c.getLevel1();
        double l2 = c.getLevel2();
        double l3 = c.getLevel3();
        double l4 = c.getLevel4();

        if(meterConsume <= 0) return s;

        if (meterConsume <= 10) {
            s = meterConsume * l1;
        } else if (meterConsume <= 20) {
            s = 10 * l1 + (meterConsume - 10) * l2;
        } else if (meterConsume <= 30) {
            s = 10 * l1 + 10 * 7.052 + (meterConsume - 20) * l3;
        } else {
            s = 10 * l1 + 10 * l2 + 10 * l3 + (meterConsume - 30) * l4;
        }
        return s;
    }

    @Override
    public double calcTax(double amount) {
        return amount * config.getConfig().getTaxRate();
    }

    @Override
    public double calcEnvironmentFee(double amount) {
        return amount * config.getConfig().getEnvironmentRate();
    }
}
