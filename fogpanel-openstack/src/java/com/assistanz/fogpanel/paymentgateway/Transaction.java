/*
 * Common Payment Transaction entity
 */
package com.assistanz.fogpanel.paymentgateway;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Sujai
 */
public class Transaction {
    
    private String UUID;
    private CreditCard creditCard;
    private BigDecimal amount;
    private Customer customer;
    private String currency;
    private String description;
    private Address bilingAddress;
    private Address shippingAddress;
    private Date transactionDateTime;
    private Boolean completed = false;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getBilingAddress() {
        return bilingAddress;
    }

    public void setBilingAddress(Address bilingAddress) {
        this.bilingAddress = bilingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    
}