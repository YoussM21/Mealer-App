package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_signup);
    }

    public void onNextClick(View view) {
        Intent intent1 = new Intent(getApplicationContext(), PaymentInfoActivity.class);
        startActivity(intent1);
    }
}