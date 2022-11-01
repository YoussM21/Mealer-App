package com.android.mealerapp;

public class Account {

    protected String id, name, lastName, email, pswd, address;


    public Account(){ }
    
    public Account(String id, String name, String lastName, String email, String pswd, String address){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pswd = pswd;
        this.address = address;

    }

    public void setId(String id) {this.id = id;}
    public String getId() {return id;}
    public void setName(String name1) {
        name = name1;
    }
    public String getName(){
        return name;
    }
    public void setLastName(String lastName1){
        lastName = lastName1;
    }
    public String getLastName(){
        return lastName;
    }
    public void setEmail(String email1) {
        email = email1;
    }
    public String getEmail(){
        return email;
    }
    public void setPswd(String pswd1) {
        pswd = pswd1;
    }
    public String getPswd() {
        return pswd;
    }
    public void setAddress(String address1){
        address = address1;
    }
    public String getAddress() {
        return address;
    }
}
