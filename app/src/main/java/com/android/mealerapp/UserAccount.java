package com.android.mealerapp;

public class UserAccount extends Account{

    private CreditCard card;

    public UserAccount(){ }

    public UserAccount(String id, String name, String lastName, String email, String pswd, String address, CreditCard card){
        super(id, "CLIENT", name, lastName, email, pswd, address);
        this.card = card;
    }

    public CreditCard getCard() {
        return card;
    }

}
