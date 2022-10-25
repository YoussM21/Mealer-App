package com.android.mealerapp;

public class UserAccount extends Account{

    private int creditCardNum;
    private int creditCardDate;
    private final String ROLE = "Client";

    public UserAccount(){ }

    public UserAccount(String name, String lastName, String email, String pswd, String address, int creditCardNum, int creditCardDate){
        super(name, lastName, email, pswd, address);
        this.creditCardNum = creditCardNum;
        this.creditCardDate = creditCardDate;
        
    }

    public String getROLE() {
        return ROLE;
    }
}
