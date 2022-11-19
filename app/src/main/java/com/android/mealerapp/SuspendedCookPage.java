package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuspendedCookPage extends AppCompatActivity {

    TextView textView_Suspension_length;
    private FirebaseAuth mAuth;
    private DatabaseReference usersDatabase;
    private String suspension_Length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspended_cook_screen);
        mAuth = FirebaseAuth.getInstance();
        usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        textView_Suspension_length = findViewById(R.id.textView_Suspension_length);



    }

    protected void onStart() {
        super.onStart();
        FirebaseUser cook = mAuth.getCurrentUser();
        if(cook != null){
            String id = cook.getUid();
            usersDatabase.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ChefAccount chef = snapshot.getValue(ChefAccount.class);
                    if (chef != null){
                        suspension_Length = chef.getSuspensionDate();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            textView_Suspension_length.setText(suspension_Length);
        }
    }

    public void onLogoutClick_2(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }

}
