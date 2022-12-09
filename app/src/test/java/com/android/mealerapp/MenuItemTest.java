package com.android.mealerapp;

import static org.junit.Assert.*;

import org.junit.Test;


public class MenuItemTest {

    @Test
    public void getMeal_isCorrect(){
        MenuItem test = new MenuItem("burger","its mid");
        String expected = "burger";
        String actual = test.getMeal();

        assertEquals(expected,actual);
    }

    @Test
    public void getDescription_isCorrect(){
        MenuItem test = new MenuItem("burger","its mid");
        String expected = "its mid";
        String actual = test.getDescription();

        assertEquals(expected,actual);
    }

    @Test
    public void getRecommend_isCorrect(){
        MenuItem test = new MenuItem("burger","its mid");
        Boolean expected = true;
        test.set_recommend(true);
        Boolean actual = test.get_recommend();

        assertEquals(expected,actual);
    }
}
