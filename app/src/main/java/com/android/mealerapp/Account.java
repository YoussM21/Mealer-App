package com.android.mealerapp;

public class Account {

    protected String name, lastName, email, pswd, address;

    public Account(){

    }
    
    public Account(String name, String lastName, String email, String pswd, String address){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pswd = pswd;
        this.address = address;
    }

}
