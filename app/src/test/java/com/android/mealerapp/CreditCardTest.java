package com.android.mealerapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreditCardTest {

    @Test
    public void getCreditCardDate_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"09/09/2003",100);
        String expected = "09/09/2003";
        String actual = testCard.getCreditCardDate();

        assertEquals(expected,actual);
    }

    @Test
    public void setNameOnCard_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"09/09/2003",100);
        testCard.setNameOnCard("fakename");
        String expected = "fakename";
        String actual = testCard.getNameOnCard();

        assertEquals(expected,actual);

    }
    @Test
    public void setCreditCardNum_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"09/09/2003",100);
        testCard.setCreditCardNum(12345678);
        long expected = 12345678;
        long actual = testCard.getCreditCardNum();

        assertEquals(expected, actual);
    }

    @Test
    public void setCreditCardDate_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"09/09/2003",100);
        testCard.setCreditCardDate("20/11/2000");
        String expected = "20/11/2000";
        String actual = testCard.getCreditCardDate();

        assertEquals(expected, actual);
    }


}