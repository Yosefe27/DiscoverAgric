package com.bluecode.weledger.models;

public class Groups {


    private String id;
    private String group_name;
    private String date_created;
    private String annual_interest_rate;
    private String status;

    public Groups() {
    }

    public Groups(String id, String group_name, String date_created, String annual_interest_rate, String status) {
        this.id = id;
        this.group_name = group_name;
        this.date_created = date_created;
        this.annual_interest_rate = annual_interest_rate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getAnnual_interest_rate() {
        return annual_interest_rate;
    }

    public void setAnnual_interest_rate(String annual_interest_rate) {
        this.annual_interest_rate = annual_interest_rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
