package com.android.mealerapp;

public class UserAccount extends Account{

    private String id;
    private CreditCard card;
    private final String ROLE = "Client";

    public UserAccount(){ }

    public UserAccount(String id, String name, String lastName, String email, String pswd, String address, CreditCard card){
        super(id, name, lastName, email, pswd, address);
        this.card = card;
    }

    public String getROLE() {
        return ROLE;
    }

    public CreditCard getCard() {
        return card;
    }

}
