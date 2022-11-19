package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class CookSuspension extends AppCompatActivity {

    ChefAccount _cook;
    String _complaints;
    private String date;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_suspension);

        Boolean isValid = false;
        EditText suspensionDate = (EditText) findViewById(R.id.suspensionDate);

        while (!isValid) {
            date = suspensionDate.getText().toString();

            isValid = verifyDate(date);
            if (!isValid) {
                Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            }
        }

        Button PermanentSuspension = findViewById(R.id.buttonsuspension2);
        PermanentSuspension.setOnClickListener((View.OnClickListener) this);
        Button TemporarySuspension = findViewById(R.id.buttonsuspension);
        TemporarySuspension.setOnClickListener((View.OnClickListener) this);

    }
            public void onClick(View view) {
            AdminWelcomePage dR = new AdminWelcomePage();
            Intent returnToComplaints = new Intent(getApplicationContext(), ComplaintsList.class);
            switch (view.getId()) {
                case R.id.buttonsuspension2:
                    dR.suspendCook(id, date);
                    startActivity(returnToComplaints);
                    break;
                case R.id.buttonsuspension:
                    dR.suspendCook(id, "Indefinite" );
                    startActivity(returnToComplaints);
                    break;

        }
    }

    private Boolean verifyDate(String date) {
        Boolean isValid = false;
        int m, d, y;

        if(date.length()<10){
            return isValid;
        }

        try {
            m = Integer.parseInt(date.substring(0, 2));
            d = Integer.parseInt(date.substring(4, 6));
            y = Integer.parseInt(date.substring(6));
        }
        catch(Exception e){
            return isValid;
        }

        if(m < 13 && m> 0){
            if(d < 32 && d > 0){
                if(y >2021){
                    isValid = true;
                }
            }
        }

        return isValid;
    }

}