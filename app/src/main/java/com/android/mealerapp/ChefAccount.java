package com.android.mealerapp;

public class ChefAccount extends Account{

    private String description;

    public ChefAccount(String id, String name, String lastName, String email, String pswd, String address, String description){
        super(id,"CHEF", name, lastName, email, pswd, address);
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}