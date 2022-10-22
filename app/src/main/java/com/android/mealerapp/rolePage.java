package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class rolePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //DatabaseReference usersDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        setContentView(R.layout.role_page);
    }

    public void onClientClick(){
        Intent intent1 = new Intent(getApplicationContext(), clientSignUpActivity.class);
        startActivity(intent1);
    }

    public void onChefClick(){

    }

    public void onLogInClick(){

    }
}