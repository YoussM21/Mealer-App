package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
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

    }

    public void onChefClick(){

    }

    public void onLogInClick(){

    }
}