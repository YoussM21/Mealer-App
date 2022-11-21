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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LogInActivity extends AppCompatActivity {
    private DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    EditText editTextUserEmail, editTextUserPswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        editTextUserEmail = findViewById(R.id.Email_input);
        editTextUserPswd = findViewById(R.id.Password_input);

    }

    @Override
    protected void onStart(){
        super.onStart();
        user = mAuth.getCurrentUser();
        if (user != null){
            Task<Void> task = user.reload();
        }
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if (user != null){
                    String id = user.getUid();
                    if (id.equals("ZJUEM6x9L1YGhDWRCiJdfpH9qdI2")){
                        updateUI("ADMIN");
                    }
                    else{
                        databaseUsers.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                DataSnapshot dataSnapshot = task.getResult();
                                Account value = dataSnapshot.getValue(Account.class);
                                if (value != null){
                                    if (value.getRole().equals("CHEF")){
                                        if (value.isBanned()){
                                            suspendedCook();
                                        }
                                        else{
                                            updateUI("CHEF");
                                        }
                                    }
                                    else if(value.getRole().equals("CLIENT")){
                                        updateUI("CLIENT");
                                    }
                                    else {
                                        Toast.makeText(LogInActivity.this, "Invalid user.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        });
                    }

                }
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




    }

    private void suspendedCook(){
        Intent i = new Intent(getApplicationContext(), SuspendedCookPage.class);
        startActivity(i);
    }
    private void updateUI(String role){
        Intent i;
        if (role.equals("ADMIN")){
            i = new Intent(getApplicationContext(), AdminWelcomePage.class);
            startActivity(i);
            finish();
        }
        else if (role.equals("CHEF")){
            i = new Intent(getApplicationContext(), CookWelcomePage.class);
            startActivity(i);
            finish();
        }
        else{
            i = new Intent(getApplicationContext(), ClientWelcomePage.class);
            startActivity(i);
            finish();
        }
    }
}