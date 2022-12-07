package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClientWelcomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser clientUser;

    ListView listViewMenu;

    List<MenuItem> meals;
    DatabaseReference databaseMenuItems;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_welcome_screen);

        meals = new ArrayList<>();
        listViewMenu = findViewById(R.id.listView_Menu);

        databaseMenuItems = FirebaseDatabase.getInstance().getReference("Meals");

        logoutButton = findViewById(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutClick_4();
            }
        });

        listViewMenu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MenuItem meal = meals.get(i);
                showUpdateDeleteDialog(meal.getMeal(), meal.getDescription());
                return true;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        clientUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                clientUser = mAuth.getCurrentUser();
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onBagClick(View view) {
        Intent intent1 = new Intent(getApplicationContext(), MyBag.class);
        startActivity(intent1);
    }

    public void onLogoutClick_4() {
        mAuth.signOut();
        Intent intent2 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent2);
    }
}