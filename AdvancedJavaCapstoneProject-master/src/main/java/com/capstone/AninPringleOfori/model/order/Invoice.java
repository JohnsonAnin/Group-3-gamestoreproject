package com.capstone.AninPringleOfori.model.order;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Invoice {

    public int invoiceId;

    @NotNull
    public String name;

    @NotNull
    public String street;

    @NotNull
    public String city;

    @NotNull
    public String state;

    @NotNull
    public String zipCode;

    @NotNull
    public String itemType;
    public int itemId;
    public double unitPrice;
    public int orderQuantity;
    public double subTotal;
    public double tax;
    public double processingFee;
    public double total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getItemId() == invoice.getItemId() &&
                Double.compare(invoice.getUnitPrice(), getUnitPrice()) == 0 &&
                getOrderQuantity() == invoice.getOrderQuantity() &&
                Double.compare(invoice.getSubTotal(), getSubTotal()) == 0 &&
                Double.compare(invoice.getTax(), getTax()) == 0 &&
                Double.compare(invoice.getProcessingFee(), getProcessingFee()) == 0 &&
                Double.compare(invoice.getTotal(), getTotal()) == 0 &&
                getName().equals(invoice.getName()) &&
                getStreet().equals(invoice.getStreet()) &&
                getCity().equals(invoice.getCity()) &&
                getState().equals(invoice.getState()) &&
                getZipCode().equals(invoice.getZipCode()) &&
                getItemType().equals(invoice.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getUnitPrice(), getOrderQuantity(), getSubTotal(), getTax(), getProcessingFee(), getTotal());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", orderQuantity=" + orderQuantity +
                ", subTotal=" + subTotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
