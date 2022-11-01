package com.android.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class ClientSignUpActivity extends AppCompatActivity {

    EditText editTextName, editTextLastName, editTextEmail, editTextPswd, editTextAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_signup);

        editTextName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        editTextEmail = findViewById(R.id.EmailAddress);
        editTextPswd = findViewById(R.id.PasswordID);
        editTextAddress = findViewById(R.id.AddressID);

    }

    public void onNextClick1(View view) {
        String name = editTextName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String pswd1 = editTextPswd.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        Intent intent1 = new Intent(getApplicationContext(), PaymentInfoActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("NAME", name);
        bundle.putString("LAST_NAME", lastName);
        bundle.putString("EMAIL", email);
        bundle.putString("PSWD", pswd1);
        bundle.putString("ADDRESS", address);

        intent1.putExtras(bundle);
        startActivity(intent1);
    }

    public void onLogInClick2(View view){
        Intent i = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(i);
    }
}