package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class CookSuspension extends AppCompatActivity {


    private String id;

    Button PermanentSuspension;
    Button TemporarySuspension;
    EditText suspensionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_suspension);

        suspensionDate = (EditText) findViewById(R.id.suspensionDate);
        Intent currentIntent = getIntent();

        id = currentIntent.getStringExtra("ID");


        PermanentSuspension = findViewById(R.id.buttonsuspension2);
        PermanentSuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suspension("Indefinite");
            }
        });

        TemporarySuspension = findViewById(R.id.buttonsuspension);
        TemporarySuspension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = suspensionDate.getText().toString();
                if (!TextUtils.isEmpty(date)){
                    if (verifyDate(date)){
                        suspension(date);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void suspension(String length) {
        Intent returnToComplaints = new Intent(getApplicationContext(), ComplaintsList.class);

        returnToComplaints.putExtra("Id",id);
        returnToComplaints.putExtra("Suspension_Length",length);
        setResult(RESULT_OK, returnToComplaints);
        finish();


    }

    private Boolean verifyDate(String date) {
        Boolean isValid = false;
        int m, d, y;

        if(date.length()<10){
            return isValid;
        }

        try {
            m = Integer.parseInt(date.substring(0, 2));
            d = Integer.parseInt(date.substring(3, 5));
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