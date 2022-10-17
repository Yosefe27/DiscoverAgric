package com.bluecode.weledger.models;

public class MyContributions {
    private String id;
    private String transaction_type;
    private String transaction_amount;
    private String transaction_month;
    private String transaction_ref_number;
    private String full_name;

    public MyContributions(String id, String transaction_type, String transaction_amount, String transaction_month, String transaction_ref_number, String full_name) {
        this.id = id;
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_month = transaction_month;
        this.transaction_ref_number = transaction_ref_number;
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_month() {
        return transaction_month;
    }

    public void setTransaction_month(String transaction_month) {
        this.transaction_month = transaction_month;
    }

    public String getTransaction_ref_number() {
        return transaction_ref_number;
    }

    public void setTransaction_ref_number(String transaction_ref_number) {
        this.transaction_ref_number = transaction_ref_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
