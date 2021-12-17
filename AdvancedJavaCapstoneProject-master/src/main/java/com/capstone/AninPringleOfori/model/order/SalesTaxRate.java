package com.capstone.AninPringleOfori.model.order;

import java.util.Objects;

public class SalesTaxRate {

    public String state;
    public double rate;

    public SalesTaxRate() {}

    public SalesTaxRate(String state, double rate) {
        this.state = state;
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Double.compare(that.getRate(), getRate()) == 0 && getState().equals(that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getRate());
    }
}
