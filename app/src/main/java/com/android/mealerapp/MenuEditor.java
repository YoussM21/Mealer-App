package com.android.mealerapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MenuEditor extends AppCompatActivity {

    EditText editTextMealName;
    EditText editTextMeal_Description;
    Button buttonAddMeal;

    DatabaseReference databaseMeals;

    private void addProduct() {

        String name = editTextMealName.getText().toString().trim();
        String description = editTextMeal_Description.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {
            String id = databaseMeals.push().getKey();
            MenuItem meal = new MenuItem(name, description);
            databaseMeals.child(id).setValue(meal);

            editTextMealName.setText("");
            editTextMeal_Description.setText("");
            Toast.makeText(this, "Meal added", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_meal_page);

        editTextMealName = findViewById(R.id.editTextMealName);
        editTextMeal_Description = findViewById(R.id.editTextMeal_Description);
        buttonAddMeal = findViewById(R.id.Addbutton);

        //adding an onclick listener to button
        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });
    }
}

