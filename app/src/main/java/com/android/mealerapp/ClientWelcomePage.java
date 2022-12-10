package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClientWelcomePage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    ListView listViewMenu;

    MealsList adapter;

    UserAccount client;
    private FirebaseUser user;
    private String clientId;

    List<Meal> availableMeals;
    DatabaseReference databaseMenuItems;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_welcome_screen);

        availableMeals = new ArrayList<>();
        listViewMenu = findViewById(R.id.listView_Menu);

        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        clientId = user.getUid();

        getClient(clientId).addOnCompleteListener(new OnCompleteListener<UserAccount>() {
            @Override
            public void onComplete(@NonNull Task<UserAccount> task) {
                client = task.getResult();

            }
        });

        adapter = new MealsList(ClientWelcomePage.this, availableMeals);


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
                showAdd_toBagDialog(meal, meal.get_mealName());
                return true;
            }
        });

        mAuth = FirebaseAuth.getInstance();

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
                    if (e.get_mealName().equals(query.trim())) {
                        int position = adapter.getPosition(e);
                        long id = adapter.getItemId(position);
                        listViewMenu.setFilterText(query);
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
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseMenuItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                availableMeals.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Meal meal = postSnapshot.getValue(Meal.class);
                    assert meal != null;
                    ChefAccount cook = meal.getCook();
                    if(!cook.isBanned() && meal.get_recommend()){
                        availableMeals.add(meal);
                    }

                    listViewMenu.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showAdd_toBagDialog(final Meal selectedMeal, final String mealName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_to_bag_page, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle(mealName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        final Button buttonAdd = dialogView.findViewById(R.id.add_meal_button);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.addMeal(selectedMeal);
                updateClient(clientId);
                b.dismiss();
            }
        });
    }

    private void updateClient(String clientId){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(clientId);
        dR.setValue(client);
    }

    private Task<UserAccount> getClient(String id){
        TaskCompletionSource<UserAccount> taskSource = new TaskCompletionSource<>();
        FirebaseDatabase.getInstance().getReference("Users").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                UserAccount client = snapshot.getValue(UserAccount.class);
                taskSource.setResult(client);
            }
        });

        return taskSource.getTask();
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