package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

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

    MealsList adapter;

    List<Meal> availableMeals;
    DatabaseReference databaseMenuItems;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_welcome_screen);

        availableMeals = new ArrayList<>();
        listViewMenu = findViewById(R.id.listView_Menu);

        adapter = new MealsList(ClientWelcomePage.this, availableMeals);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Meal meal = availableMeals.get(i);
                return true;
            }
        });

        mAuth = FirebaseAuth.getInstance();
        clientUser = mAuth.getCurrentUser();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search_Meal);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String query) {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                for (Meal e : availableMeals) {
                    if (e.get_mealName().equals(query)) {
                        adapter.getFilter().filter(query);
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                return false;
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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