package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CookWelcomePage extends AppCompatActivity {

    EditText editTextMealName;
    EditText editTextMeal_Description;

    ListView listViewMenuItems;

    List<MenuItem> meals;
    DatabaseReference databaseMenuItems;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_page);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        meals = new ArrayList<>();
        listViewMenuItems = findViewById(R.id.listView_Menu);



        databaseMenuItems = FirebaseDatabase.getInstance().getReference("Meals");

        logoutButton = findViewById(R.id.logout_button3);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutClick_3();
            }
        });

        listViewMenuItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MenuItem meal = meals.get(i);
                showUpdateDeleteDialog(meal.getMeal(), meal.getDescription());
                return true;
            }
        });

    }

    public void onNewMealClick(View view){
        Intent intent2 = new Intent(getApplicationContext(), MenuEditor.class);
        startActivity(intent2);
    }

    public void onLogoutClick_3(){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }

    private void showUpdateDeleteDialog(final String MealID, String MealName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_menu, null);
        dialogBuilder.setView(dialogView);


        final Button buttonUpdate = dialogView.findViewById(R.id.Update_meal);
        final Button buttonDelete = dialogView.findViewById(R.id.Delete_meal);

        dialogBuilder.setTitle(MealName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextMealName.getText().toString().trim();
                String description = editTextMeal_Description.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateMeal(MealID, name, description);
                    b.dismiss();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeal(MealID);
                b.dismiss();
            }
        });
    }

    private void updateMeal(String id, String name, String description) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("meals").child(id);
        MenuItem meal = new MenuItem(name, description);
        dR.setValue(meal);

        Toast.makeText(getApplicationContext(), "Product Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteMeal(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Meals").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Meal Deleted", Toast.LENGTH_LONG).show();

    }

}