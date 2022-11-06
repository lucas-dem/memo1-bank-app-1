package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long transactionId;
    private Long accountCbu;
    private Double amount;

    public Transaction(){}

    public void setTransactionId(Long transactionId){
        this.transactionId = transactionId;
    }

    public Long getTransactionId(){
        return this.transactionId;
    }

    public void withdraw(){
        this.amount =- this.amount;
    }

    public Double getAmount(){
        return this.amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public Long getAccountCbu(){
        return this.accountCbu;
    }
    }