package com.bluecode.weledger.models;

public class LoanRequests {

    private String id;
    private String amount;
    private String duration;
    private String contributor_id;
    private String loan_reason;
    private String group_id;
    private String start_date;
    private String end_date;
    private String secretary_approval;
    private String treasurer_approval;
    private String chairperson_approval;
    private String secretary_id;
    private String treasurer_id;
    private String chairperson_id;
    private String secretary_approval_date;
    private String treasurer_approval_date;
    private String chairperson_approval_date;
    private String secretary_approval_comment;
    private String treasurer_approval_comment;
    private String chairperson_approval_comment;
    private String date_created;

    public LoanRequests() {
    }

    public LoanRequests(String id, String amount, String duration, String contributor_id, String loan_reason, String group_id, String start_date, String end_date, String secretary_approval, String treasurer_approval, String chairperson_approval, String secretary_id, String treasurer_id, String chairperson_id, String secretary_approval_date, String treasurer_approval_date, String chairperson_approval_date, String secretary_approval_comment, String treasurer_approval_comment, String chairperson_approval_comment, String date_created) {
        this.id = id;
        this.amount = amount;
        this.duration = duration;
        this.contributor_id = contributor_id;
        this.loan_reason = loan_reason;
        this.group_id = group_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.secretary_approval = secretary_approval;
        this.treasurer_approval = treasurer_approval;
        this.chairperson_approval = chairperson_approval;
        this.secretary_id = secretary_id;
        this.treasurer_id = treasurer_id;
        this.chairperson_id = chairperson_id;
        this.secretary_approval_date = secretary_approval_date;
        this.treasurer_approval_date = treasurer_approval_date;
        this.chairperson_approval_date = chairperson_approval_date;
        this.secretary_approval_comment = secretary_approval_comment;
        this.treasurer_approval_comment = treasurer_approval_comment;
        this.chairperson_approval_comment = chairperson_approval_comment;
        this.date_created = date_created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getContributor_id() {
        return contributor_id;
    }

    public void setContributor_id(String contributor_id) {
        this.contributor_id = contributor_id;
    }

    public String getLoan_reason() {
        return loan_reason;
    }

    public void setLoan_reason(String loan_reason) {
        this.loan_reason = loan_reason;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSecretary_approval() {
        return secretary_approval;
    }

    public void setSecretary_approval(String secretary_approval) {
        this.secretary_approval = secretary_approval;
    }

    public String getTreasurer_approval() {
        return treasurer_approval;
    }

    public void setTreasurer_approval(String treasurer_approval) {
        this.treasurer_approval = treasurer_approval;
    }

    public String getChairperson_approval() {
        return chairperson_approval;
    }

    public void setChairperson_approval(String chairperson_approval) {
        this.chairperson_approval = chairperson_approval;
    }

    public String getSecretary_id() {
        return secretary_id;
    }

    public void setSecretary_id(String secretary_id) {
        this.secretary_id = secretary_id;
    }

    public String getTreasurer_id() {
        return treasurer_id;
    }

    public void setTreasurer_id(String treasurer_id) {
        this.treasurer_id = treasurer_id;
    }

    public String getChairperson_id() {
        return chairperson_id;
    }

    public void setChairperson_id(String chairperson_id) {
        this.chairperson_id = chairperson_id;
    }

    public String getSecretary_approval_date() {
        return secretary_approval_date;
    }

    public void setSecretary_approval_date(String secretary_approval_date) {
        this.secretary_approval_date = secretary_approval_date;
    }

    public String getTreasurer_approval_date() {
        return treasurer_approval_date;
    }

    public void setTreasurer_approval_date(String treasurer_approval_date) {
        this.treasurer_approval_date = treasurer_approval_date;
    }

    public String getChairperson_approval_date() {
        return chairperson_approval_date;
    }

    public void setChairperson_approval_date(String chairperson_approval_date) {
        this.chairperson_approval_date = chairperson_approval_date;
    }

    public String getSecretary_approval_comment() {
        return secretary_approval_comment;
    }

    public void setSecretary_approval_comment(String secretary_approval_comment) {
        this.secretary_approval_comment = secretary_approval_comment;
    }

    public String getTreasurer_approval_comment() {
        return treasurer_approval_comment;
    }

    public void setTreasurer_approval_comment(String treasurer_approval_comment) {
        this.treasurer_approval_comment = treasurer_approval_comment;
    }

    public String getChairperson_approval_comment() {
        return chairperson_approval_comment;
    }

    public void setChairperson_approval_comment(String chairperson_approval_comment) {
        this.chairperson_approval_comment = chairperson_approval_comment;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
