package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;


public class LogInActivity extends AppCompatActivity {
    private DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;
    EditText editTextUserEmail, editTextUserPswd;

    private List<ChefAccount> chefList;
    private List<UserAccount> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        chefList = new ArrayList<ChefAccount>();
        clientList = new ArrayList<UserAccount>();

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        editTextUserEmail = findViewById(R.id.Email_input);
        editTextUserPswd = findViewById(R.id.Password_input);
    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chefList.clear();
                clientList.clear();

                for (DataSnapshot postSnapshot: snapshot.child("Chefs").getChildren()) {
                    ChefAccount chef = postSnapshot.getValue(ChefAccount.class);
                    chefList.add(chef);
                }

                for (DataSnapshot postSnapshot: snapshot.child("Clients").getChildren()) {
                    UserAccount client = postSnapshot.getValue(UserAccount.class);
                    clientList.add(client);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void signInUser(String email, String pswd){
        mAuth.signInWithEmailAndPassword(email, pswd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LogInActivity.this, "Sign In success.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LogInActivity.this, "Sign In failure.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onLoginClick1(View view){
        String userEmail = editTextUserEmail.getText().toString().trim();
        String userPswd = editTextUserPswd.getText().toString().trim();

        signInUser(userEmail,userPswd);
        Class destination = null;
        for (ChefAccount i : chefList) {
            if (i.getEmail().equals(userEmail) && i.getPswd().equals(userPswd)){
                destination = CookWelcomePage.class;
                break;
            }
        }

        for (UserAccount i : clientList) {
            if (i.getEmail().equals(userEmail) && i.getPswd().equals(userPswd)){
                destination = ClientWelcomePage.class;
                break;
            }
        }
        if(destination != null){
            Intent page = new Intent(getApplicationContext(), destination);
            startActivity(page);
        }
        else {
            Toast.makeText(LogInActivity.this, "Cannot proceed.", Toast.LENGTH_SHORT).show();
        }



    }
}