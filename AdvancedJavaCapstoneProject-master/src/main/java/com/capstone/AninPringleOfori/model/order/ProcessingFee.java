package com.capstone.AninPringleOfori.model.order;

import java.util.Objects;

public class ProcessingFee {
    public String productType;
    public double fee;

    public ProcessingFee() {
    }

    public ProcessingFee(String productType, double fee) {
        this.fee = fee;
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return Double.compare(that.getFee(), getFee()) == 0 && getProductType().equals(that.getProductType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }
}
