package com.example.cs360_appdevproposal_jamiejavis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton, createAccountButton;
    UserDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        dbHelper = new UserDatabase(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (validateLogin(username, password)) {
                // next activity after login
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent logIn = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(logIn);
            } else {
                // error message
                Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        });

        createAccountButton.setOnClickListener(v -> {
            // registration activity
            Intent newAccount = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(newAccount);
        });
    }

    private boolean validateLogin(String username, String password) {
        // validation of login for real world use
        return true;
    }

}
