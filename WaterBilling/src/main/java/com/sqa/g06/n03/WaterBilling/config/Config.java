package com.sqa.g06.n03.WaterBilling.config;

import jakarta.persistence.*;

@Entity
@Table(name = "config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "environment_rate")
    private int environmentRate;

    @Column(name = "tax_rate")
    private int taxRate;

    @Column(name = "level1")
    private double level1;

    @Column(name = "level2")
    private double level2;

    @Column(name = "level3")
    private double level3;

    @Column(name = "level4")
    private double level4;

    public Config() {
    }

    public Config(int environmentRate, int taxRate, double level1, double level2, double level3, double level4) {
        this.environmentRate = environmentRate;
        this.taxRate = taxRate;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
    }

    public int getEnvironmentRate() {
        return environmentRate;
    }

    public void setEnvironmentRate(int environmentRate) {
        this.environmentRate = environmentRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public double getLevel1() {
        return level1;
    }

    public void setLevel1(double level1) {
        this.level1 = level1;
    }

    public double getLevel2() {
        return level2;
    }

    public void setLevel2(double level2) {
        this.level2 = level2;
    }

    public double getLevel3() {
        return level3;
    }

    public void setLevel3(double level3) {
        this.level3 = level3;
    }

    public double getLevel4() {
        return level4;
    }

    public void setLevel4(double level4) {
        this.level4 = level4;
    }

}
