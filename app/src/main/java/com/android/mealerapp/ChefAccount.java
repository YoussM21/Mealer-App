package com.android.mealerapp;

public class ChefAccount extends Account{

    private String description;

    public ChefAccount(String name, String lastName, String email, String pswd, String address, String description){
        super(name, lastName, email, pswd, address);
        this.description = description;
    }
}