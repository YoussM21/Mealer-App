package com.android.mealerapp;

import static org.junit.Assert.*;

import org.junit.Test;


public class MenuItemTest {

    @Test
    public void getMeal_isCorrect(){
        ChefAccount cook1 = new ChefAccount();
        Meal test = new Meal(cook1,"burger","its mid");
        String expected = "burger";
        String actual = test.get_mealName();

        assertEquals(expected,actual);
    }

    @Test
    public void getDescription_isCorrect(){
        ChefAccount cook1 = new ChefAccount();
        Meal test = new Meal(cook1,"burger","its mid");
        String expected = "its mid";
        String actual = test.getDescription();

        assertEquals(expected,actual);
    }

    @Test
    public void getRecommend_isCorrect(){
        ChefAccount cook1 = new ChefAccount();
        Meal test = new Meal(cook1,"burger","its mid");
        Boolean expected = true;
        test.set_recommend(true);
        Boolean actual = test.get_recommend();

        assertEquals(expected,actual);
    }
}
