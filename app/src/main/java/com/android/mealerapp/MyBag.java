package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBag extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser clientUser;
    DatabaseReference clientInfo;
    private List<Meal> myOrders;
    UserAccount client;
    private String clientId;
    ListView listViewOrders;
    MealsList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_bag_page);

        mAuth = FirebaseAuth.getInstance();
        clientUser = mAuth.getCurrentUser();
        assert clientUser != null;
        clientId = clientUser.getUid();
        clientInfo = FirebaseDatabase.getInstance().getReference("Users").child(clientId);

        myOrders = new ArrayList<>();
        listViewOrders = findViewById(R.id.listView_Meal_bag);
        adapter = new MealsList(MyBag.this, myOrders);

        getClient(clientId).addOnCompleteListener(new OnCompleteListener<UserAccount>() {
            @Override
            public void onComplete(@NonNull Task<UserAccount> task) {
                client = task.getResult();
                if (client.getMealList().isEmpty()){
                    Toast.makeText(getApplicationContext(), "No orders", Toast.LENGTH_LONG).show();
                }
            }
        });

        listViewOrders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                Meal meal = myOrders.get(i);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        clientInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myOrders = snapshot.child("mealList").getValue(new GenericTypeIndicator<List<Meal>>() {});
                listViewOrders.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    public void onBuyClick(View view){
        Intent intent1 = new Intent(getApplicationContext(),ClientWelcomePage.class);
        startActivity(intent1);
    }
}