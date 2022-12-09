package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class cookOrdersPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_orders_page);
    }

    public void onLogoutClick_5(){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }
}
