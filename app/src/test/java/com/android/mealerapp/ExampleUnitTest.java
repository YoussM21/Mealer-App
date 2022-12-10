package com.android.mealerapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getComplaint_isCorrect(){
        boolean expected = true;
        boolean actual;

        ChefAccount testChef = new ChefAccount("test","test","test","test","test","test","test");
        Complaint testComplaint = new Complaint("test",testChef);

        actual = testComplaint.getComplaint() != null;
        assertEquals(expected, actual);
    }
    @Test
    public void getCookName_isCorrect(){
        ChefAccount testChef = new ChefAccount("test","name1","test","test","test","test","test");
        Complaint testComplaint = new Complaint("test",testChef);
        String expected = "name1";
        String actual = testComplaint.getCookName();

        assertEquals(expected,actual);
    }

    @Test
    public void getCreditCardNum_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"test",100);
        long expected = 12345678;
        long actual = testCard.getCreditCardNum();

        assertEquals(expected,actual);
    }

    @Test
    public void getCreditCardCCV_isCorrect(){
        CreditCard testCard = new CreditCard("test",12345678,"test",100);
        int expected = 100;
        int actual = testCard.getCreditCardCVV();

        assertEquals(expected,actual);
    }

}