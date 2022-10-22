package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminWelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_welcome_screen);
    }

    public void onLogoutClick(View view){
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }
}