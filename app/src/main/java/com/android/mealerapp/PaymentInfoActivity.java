package com.android.mealerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PaymentInfoActivity extends AppCompatActivity {
    private static final String TAG = "ClientSignup";
    private DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;
    private FirebaseUser client;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Bundle data;
    private CreditCard card;

    EditText editTextNameOnCard, editTextCardNum, editTextValidDate, editTextCVV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        client = mAuth.getCurrentUser();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_info);

        editTextNameOnCard = findViewById(R.id.CardName);
        editTextCardNum = findViewById(R.id.CardNumber);
        editTextValidDate = findViewById(R.id.EXPdate);
        editTextCVV = findViewById(R.id.CVV_value);

        Intent currentIntent = getIntent();
        data = currentIntent.getExtras();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                client = firebaseAuth.getCurrentUser();
                if(client != null){
                    String id = client.getUid();
                    Account clientAccount;
                    clientAccount = new UserAccount(id, data.getString("NAME"), data.getString("LAST_NAME"),
                            data.getString("EMAIL"), data.getString("PSWD"), data.getString("ADDRESS"), card);
                    databaseUsers.child(id).setValue(clientAccount);
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

    private void createClientAccount(String email, String pswd){
        mAuth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(PaymentInfoActivity.this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createClientAccount:success");
                Toast.makeText(PaymentInfoActivity.this, "Authentication success.", Toast.LENGTH_LONG).show();
            }
            else {
                Log.w(TAG, "createClientAccount:failure", task.getException());
                Toast.makeText(PaymentInfoActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onSignUpClick1(View view){
        long cardNum = 0;
        int cvv = 0;
        try {
            cardNum = Long.parseLong(editTextCardNum.getText().toString().trim());
        }
        catch (NumberFormatException e){
            Toast.makeText(PaymentInfoActivity.this, "Invalid card number", Toast.LENGTH_LONG).show();
        }

        try {
            cvv = Integer.parseInt(editTextCVV.getText().toString().trim());
        }
        catch (NumberFormatException e){
            Toast.makeText(PaymentInfoActivity.this, "Invalid cvv number", Toast.LENGTH_LONG).show();
        }

        String nameCard = editTextNameOnCard.getText().toString().trim();
        String date = editTextValidDate.getText().toString().trim();
        card = new CreditCard(nameCard, cardNum, date, cvv);



        String email = data.getString("EMAIL");
        String password = data.getString("PSWD");
        createClientAccount(email, password);

    }

    public void updateUI(){
        Intent i = new Intent(getApplicationContext(), ClientWelcomePage.class);
        startActivity(i);
        finish();
    }
}