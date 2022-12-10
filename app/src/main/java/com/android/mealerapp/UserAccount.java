package com.android.mealerapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAccount extends Account{

    private CreditCard card;
    private List<Meal> currentOrders;

    public UserAccount(){}

    public UserAccount(String id, String name, String lastName, String email, String pswd, String address, CreditCard card){
        super(id, "CLIENT", name, lastName, email, pswd, address);
        this.card = card;
        currentOrders = new ArrayList<>();
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {this.card = card;}

    public void clearOrders(){
        currentOrders.clear();
    }

    public void addMeal(Meal meal){
        if (currentOrders == null){
            currentOrders = new ArrayList<>();
        }
        currentOrders.add(meal);
    }

    public List<Meal> getMealList(){
        // Return an empty list if there are no meals available
        if (currentOrders == null || currentOrders.isEmpty()) {
            return Collections.emptyList();
        }
        return currentOrders;
    }

    public Meal removeMeal(int index){
        return currentOrders.remove(index);
    }

    public Meal getMeal(int index){
        return currentOrders.get(index);
    }

}
