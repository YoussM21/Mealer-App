package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CookSignupActivity extends AppCompatActivity {
    private static final String TAG = "CookSignup";

    EditText editTextName, editTextLastName, editTextEmail, editTextPswd, editTextAddress, editTextDescription;

    private String name, lastName, email, pswd1, address, description;

    private DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;
    private FirebaseUser chef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ChefAccount chefAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        chef = mAuth.getCurrentUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_signup);

        editTextName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        editTextEmail = findViewById(R.id.EmailAddress);
        editTextPswd = findViewById(R.id.PasswordID);
        editTextAddress = findViewById(R.id.AddressID);
        editTextDescription = findViewById(R.id.DescriptionID);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                chef = firebaseAuth.getCurrentUser();
                if(chef != null ){
                    String id = chef.getUid();
                    chefAccount = new ChefAccount(id, name, lastName, email, pswd1, address, description);
                    databaseUsers.child("Chefs").child(id).setValue(chefAccount);
                    updateUI();
                }
            }
        };
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void createChefAccount(String email, String pswd) {
        mAuth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(CookSignupActivity.this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createChefAccount:success");
                Toast.makeText(CookSignupActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
            }
            else {
                Log.w(TAG, "createChefAccount:failure", task.getException());
                Toast.makeText(CookSignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void onSignUpClick(View view) {
        name = editTextName.getText().toString().trim();
        lastName = editTextLastName.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();
        pswd1 = editTextPswd.getText().toString().trim();
        address = editTextAddress.getText().toString().trim();
        description = editTextDescription.getText().toString().trim();

        createChefAccount(email, pswd1);

    }

    public void updateUI(){
        Intent i = new Intent(getApplicationContext(), CookWelcomePage.class);
        startActivity(i);
        finish();
    }
}