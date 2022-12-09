package com.android.mealerapp;

import java.util.ArrayList;
import java.util.List;

public class ChefAccount extends Account{

    private String description, suspensionDate;
    private List<Meal> meals;

    public ChefAccount(){}

    public ChefAccount(String id, String name, String lastName, String email, String pswd, String address, String description){
        super(id,"CHEF", name, lastName, email, pswd, address);
        this.description = description;
        suspensionDate = "Invalid";
        meals = new ArrayList<Meal>();
    }

    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public Meal getMeal(int index){
        return meals.get(index);
    }
    public String getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(String suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}