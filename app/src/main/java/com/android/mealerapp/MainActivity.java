package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        if (user != null){
            String id = user.getUid();
            if (id.equals("ZJUEM6x9L1YGhDWRCiJdfpH9qdI2")){
                reload("ADMIN");
            }
            else {
                usersDatabase.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot = task.getResult();
                        Account value = dataSnapshot.getValue(Account.class);
                        if (value != null){
                            if (value.getRole().equals("CHEF")){
                                if (value.isBanned()){
                                    suspendedChef();
                                }
                                else {
                                    reload("CHEF");
                                }
                            }
                            else if(value.getRole().equals("CLIENT")){
                                reload("CLIENT");
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Invalid user.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
            }
        }
        else{
            loadSignUpPage();
        }


    }

    private void loadSignUpPage(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),rolePage.class);
                startActivity(i);

                finish();
            }
        }, 2000);

    }

    private void suspendedChef(){
        Intent i = new Intent(getApplicationContext(), SuspendedCookPage.class);
        startActivity(i);
    }
    private void reload(String role){
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