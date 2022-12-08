package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ClientWelcomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser clientUser;
    SearchView searchMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_welcome_screen);
        mAuth = FirebaseAuth.getInstance();
        clientUser = mAuth.getCurrentUser();

        searchMeal = findViewById(R.id.search_Meal);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                clientUser = mAuth.getCurrentUser();
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onLogoutClick_3(View view){
        mAuth.signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }

}