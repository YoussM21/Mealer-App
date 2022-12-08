package com.android.mealerapp;


public class MenuItem {
    private String _meal;
    private String _description;
    private Boolean _recommend;
    private String id;
    private ChefAccount cook;


    public MenuItem(){}

    public MenuItem(ChefAccount cook, String meal, String description){
        this._meal = meal;
        this._description = description;
        _recommend = false;
        this.cook = cook;
    }

    public ChefAccount getCook() {return cook;}

    public void setCook(ChefAccount cook) {this.cook = cook;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getMeal() {
        return _meal;
    }

    public void setMeal(String meal) {
        this._meal = meal;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) { this._description = description;}

    public Boolean get_recommend() { return _recommend; }

    public void set_recommend(Boolean _recommend) { this._recommend = _recommend; }
}

