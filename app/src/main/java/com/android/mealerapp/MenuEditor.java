package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuEditor extends AppCompatActivity {

    EditText editTextMealName;
    EditText editTextMeal_Description;
    CheckBox Recommend_Button;
    Button buttonAddMeal;

    DatabaseReference databaseMeals;

    private boolean recommend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_meal_page);

        databaseMeals = FirebaseDatabase.getInstance().getReference("Meals");

        editTextMealName = findViewById(R.id.editTextMealName);
        editTextMeal_Description = findViewById(R.id.editTextMeal_Description);
        Recommend_Button = findViewById(R.id.Recommend_button);
        buttonAddMeal = findViewById(R.id.Addbutton);

        //adding an onclick listener to button
        buttonAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeal();
            }
        });

        Recommend_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommend = !recommend;
            }
        });
    }

    private void addMeal() {

        String name = editTextMealName.getText().toString().trim();
        String description = editTextMeal_Description.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {
            String id = databaseMeals.push().getKey();
            String cookId = getIntent().getStringExtra("ID");
            Task<ChefAccount> findCook = getCook(cookId);

            findCook.addOnCompleteListener(new OnCompleteListener<ChefAccount>() {
                @Override
                public void onComplete(@NonNull Task<ChefAccount> task) {
                    ChefAccount cook = task.getResult();
                    MenuItem meal = new MenuItem(cook, name, description);
                    meal.setId(id);
                    if (recommend){
                        meal.set_recommend(true);
                    }
                    assert id != null;
                    databaseMeals.child(id).setValue(meal);

                    editTextMealName.setText("");
                    editTextMeal_Description.setText("");
                    Toast.makeText(getApplicationContext(), "Meal added", Toast.LENGTH_LONG).show();

                    Intent i2 = new Intent(getApplicationContext(), CookWelcomePage.class);
                    startActivity(i2);
                    finish();
                }
            });
        }
        else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private Task<ChefAccount> getCook(String id){
        TaskCompletionSource<ChefAccount> taskSource = new TaskCompletionSource<>();
        FirebaseDatabase.getInstance().getReference("Users").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                ChefAccount cook = snapshot.getValue(ChefAccount.class);
                taskSource.setResult(cook);
            }
        });

        return taskSource.getTask();
    }
}

