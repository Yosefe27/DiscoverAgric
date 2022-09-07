package com.bluecode.weledger.models;

public class Members {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String nrc;
    private String password;
    private String address;
    private String user_role;
    private String group_id;
    private String avatar;
    private String chairperson_approval;
    private String treasurer_approval;
    private String secretary_approval;
    private String membership_status;

    public Members() {
    }

    public Members(String id, String firstname, String lastname, String email, String nrc, String password, String address, String user_role, String group_id, String avatar, String chairperson_approval, String treasurer_approval, String secretary_approval, String membership_status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nrc = nrc;
        this.password = password;
        this.address = address;
        this.user_role = user_role;
        this.group_id = group_id;
        this.avatar = avatar;
        this.chairperson_approval = chairperson_approval;
        this.treasurer_approval = treasurer_approval;
        this.secretary_approval = secretary_approval;
        this.membership_status = membership_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getChairperson_approval() {
        return chairperson_approval;
    }

    public void setChairperson_approval(String chairperson_approval) {
        this.chairperson_approval = chairperson_approval;
    }

    public String getTreasurer_approval() {
        return treasurer_approval;
    }

    public void setTreasurer_approval(String treasurer_approval) {
        this.treasurer_approval = treasurer_approval;
    }

    public String getSecretary_approval() {
        return secretary_approval;
    }

    public void setSecretary_approval(String secretary_approval) {
        this.secretary_approval = secretary_approval;
    }

    public String getMembership_status() {
        return membership_status;
    }

    public void setMembership_status(String membership_status) {
        this.membership_status = membership_status;
    }
}
