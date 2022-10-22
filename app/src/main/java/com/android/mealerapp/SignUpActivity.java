package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    public void onClientClick(View view){
        Intent intent1 = new Intent(getApplicationContext(), ClientSignUpActivity.class);
        startActivity(intent1);
    }

    public void onChefClick(View view){
        Intent intent1 = new Intent(getApplicationContext(), CookSignupActivity.class);
        startActivity(intent1);
    }

    public void onLogInClick(View view){
        Intent intent2 = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(intent2);
    }
}