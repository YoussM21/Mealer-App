package com.android.mealerapp;

public class UserAccount extends Account{

    private int creditCardNum;

    public UserAccount(String name, String lastName, String email, String pswd, String address, int creditCardNum){
        super(name, lastName, email, pswd, address);
        this.creditCardNum = creditCardNum;
    }

}