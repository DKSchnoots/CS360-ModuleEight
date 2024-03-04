package com.example.cs360_appdevproposal_jamiejavis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button registerButton;
    UserDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new UserDatabase(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    // error message if username or password is empty
                    Toast.makeText(RegistrationActivity.this, "Username and password are required", Toast.LENGTH_SHORT).show();
                } else {
                    // save user and pass to database
                    User user = new User(username, password); // Import User class
                    dbHelper.addUser(user);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
