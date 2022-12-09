package com.android.mealerapp;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account{

    private CreditCard card;
    private List<Meal> currentOrders;

    public UserAccount(){ }

    public UserAccount(String id, String name, String lastName, String email, String pswd, String address, CreditCard card){
        super(id, "CLIENT", name, lastName, email, pswd, address);
        this.card = card;
        currentOrders = new ArrayList<>();
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {this.card = card;}

    public void addMeal(Meal meal){
        currentOrders.add(meal);
    }

    public Meal getMeal(int index){
        return currentOrders.get(index);
    }

}
