package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private String suspension_Length = "testing";
    private String id;

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
            id = cook.getUid();
            usersDatabase.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    DataSnapshot dataSnapshot = task.getResult();
                    ChefAccount cook = dataSnapshot.getValue(ChefAccount.class);
                    if (cook != null){
                        suspension_Length = cook.getSuspensionDate();
                        textView_Suspension_length.setText(suspension_Length);
                    }
                }
            });

        }
    }

    public void onLogoutClick_2(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }

}
