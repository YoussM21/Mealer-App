package com.android.mealerapp;


public class MenuItem {
    private String _meal;
    private String _description;
    private Boolean _recommend;


    public MenuItem(){}

    public MenuItem(String meal, String description){
        this._meal = meal;
        this._description = description;
        _recommend = false;
    }

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

