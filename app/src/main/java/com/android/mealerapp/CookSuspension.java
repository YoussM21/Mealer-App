package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CookSuspension extends AppCompatActivity {

    ChefAccount _cook;
    String _complaints;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_suspension);
    }

    private void updateInfo (){

        Boolean isValid = false;
        EditText suspensionDate = (EditText) findViewById(R.id.suspensionDate);

        while(!isValid) {
            date = suspensionDate.getText().toString();

            isValid = verifyDate(date);
            if (!isValid) {
                Toast.makeText(getApplicationContext(), "Invalid date", Toast.LENGTH_SHORT).show();
            }
        }
        Button Suspend = findViewById(R.id.buttonsuspension);
        Suspend .setOnClickListener((View.OnClickListener) this);
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