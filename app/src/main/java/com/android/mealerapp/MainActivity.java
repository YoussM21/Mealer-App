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
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference usersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        usersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        if (user != null){
            String id = user.getUid();
            if (id.equals("AlHP357zDcM5Fq48mmAWrSs1XrH3")){
                reload("ADMIN");
            }

            usersDatabase.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    DataSnapshot dataSnapshot = task.getResult();
                    Account value = dataSnapshot.getValue(Account.class);
                    if (value != null){
                        if (value.getRole().equals("CHEF")){
                            reload("CHEF");
                        }
                        else {
                            reload("CLIENT");
                        }
                    }

                }
            });
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