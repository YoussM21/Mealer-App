package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CookSignupActivity extends AppCompatActivity {
    private static final String TAG = "CookSignup";

    EditText editTextName, editTextLastName, editTextEmail, editTextPswd, editTextAddress, editTextDescription;


    private DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_signup);

        editTextName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        editTextEmail = findViewById(R.id.EmailAddress);
        editTextPswd = findViewById(R.id.PasswordID);
        editTextAddress = findViewById(R.id.AddressID);
        editTextDescription = findViewById(R.id.DescriptionID);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ChefAccount chef = snapshot.getValue(ChefAccount.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void createChefAccount(String email, String pswd) {
        mAuth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createChefAccount:success");
                    Toast.makeText(CookSignupActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.w(TAG, "createChefAccount:failure", task.getException());
                    Toast.makeText(CookSignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onSignUpClick(View view) {
        String name = editTextName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String pswd1 = editTextPswd.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        createChefAccount(email, pswd1);
        String id = mAuth.getCurrentUser().getUid();

        ChefAccount chefAccount = new ChefAccount(id, name, lastName, email, pswd1, address, description);

        databaseUsers.child("Chefs").child(id).setValue(chefAccount);
        Intent i = new Intent(getApplicationContext(), CookWelcomePage.class);
        startActivity(i);




    }
}