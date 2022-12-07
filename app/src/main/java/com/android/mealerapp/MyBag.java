package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyBag extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser clientUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_bag_page);

        mAuth = FirebaseAuth.getInstance();
        clientUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                clientUser = mAuth.getCurrentUser();
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onBuyClick(View view){
        Intent intent1 = new Intent(getApplicationContext(),ClientWelcomePage.class);
        startActivity(intent1);
    }
}