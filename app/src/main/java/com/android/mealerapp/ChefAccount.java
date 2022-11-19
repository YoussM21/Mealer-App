package com.android.mealerapp;

public class ChefAccount extends Account{

    private String description, suspensionDate;
    private boolean isSuspended;

    public ChefAccount(){}

    public ChefAccount(String id, String name, String lastName, String email, String pswd, String address, String description){
        super(id,"CHEF", name, lastName, email, pswd, address);
        this.description = description;
        suspensionDate = "Invalid";
        isSuspended = false;
    }

    public ChefAccount(String id, String name, String lastName, String email, String pswd, String address, String description, Boolean isSuspende, String suspensionDate){
        super(id,"CHEF", name, lastName, email, pswd, address);
        this.description = description;
        this.suspensionDate = suspensionDate;
        this.isSuspended = isSuspended;
    }

    public String getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(String suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}