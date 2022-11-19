package com.android.mealerapp;

public class CreditCard {
    private String nameOnCard;
    private long creditCardNum;
    private String creditCardDate;
    private int creditCardCVV;


    public CreditCard(String name, long num, String date, int cvv){
        nameOnCard = name;
        creditCardNum = num;
        creditCardDate = date;
        creditCardCVV = cvv;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }
    public long getCreditCardNum() {
        return creditCardNum;
    }
    public String getCreditCardDate() {
        return creditCardDate;
    }
    public int getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    public void setCreditCardNum(long creditCardNum) {
        this.creditCardNum = creditCardNum;
    }
    public void setCreditCardDate(String creditCardDate) {
        this.creditCardDate = creditCardDate;
    }
    public void setCreditCardCVV(int creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }
}
