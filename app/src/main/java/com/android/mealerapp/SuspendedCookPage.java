package com.android.mealerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuspendedCookPage extends AppCompatActivity {

    TextView textView_Suspension_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suspended_cook_screen);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        textView_Suspension_length = findViewById(R.id.textView_Suspension_length);

    }

    public void onLogoutClick_2(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(getApplicationContext(), rolePage.class);
        startActivity(intent1);
    }

}
