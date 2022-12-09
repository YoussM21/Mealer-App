package com.android.mealerapp;


public class Meal {
    private String _mealName;
    private String _description;
    private Boolean _recommend;
    private String id;
    private ChefAccount cook;


    public Meal(){}

    public Meal(ChefAccount cook, String mealName, String description){
        this._mealName = mealName;
        this._description = description;
        _recommend = false;
        this.cook = cook;
    }

    public ChefAccount getCook() {return cook;}

    public void setCook(ChefAccount cook) {this.cook = cook;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String get_mealName() {
        return _mealName;
    }

    public void set_mealName(String meal) {
        this._mealName = meal;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) { this._description = description;}

    public Boolean get_recommend() { return _recommend; }

    public void set_recommend(Boolean _recommend) { this._recommend = _recommend; }
}

