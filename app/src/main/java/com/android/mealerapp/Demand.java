package com.android.mealerapp;

//livrable 4
public class Demand {

    private ChefAccount _cook;
    private UserAccount _client;
    private MenuItem _item;
    private boolean approved;

    public Demand(ChefAccount cook, UserAccount client, MenuItem item){
        this._cook = cook;
        this._client = client;
        this._item = item;
    }

    //this here gives the option to accept or decline the demand
    public void set_approved(boolean approved){
        this.approved = approved;
    }
    public boolean get_approved(){
        return approved;
    }

    public ChefAccount get_cook(){
        return _cook;
    }
    public String getCookName(){
        return _cook.getName();
    }
    public void set_cook(ChefAccount cook){
        this._cook = cook;
    }

    public UserAccount get_client(){
        return _client;
    }
    public void set_client(UserAccount client) {
        this._client = client;
    }

    public MenuItem get_item(){
        return _item;
    }
    public void set_item(MenuItem item) {
        this._item = item;
    }
}
