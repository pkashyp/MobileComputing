package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class register extends AppCompatActivity {

    // Declare UI components for user input and navigation.
    EditText firstname, lastname, email, phone, password; // Fields for user registration details.
    Button btnregister, btnlogin; // Buttons for registration and navigating to login.
    dbConnect db; // Database helper for handling user data operations.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge rendering for immersive UI.
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize database helper.
        db = new dbConnect(this);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link UI components to their respective layout elements.
        email = findViewById(R.id.regemail); // EditText for user email.
        firstname = findViewById(R.id.regfirstname); // EditText for user's first name.
        lastname = findViewById(R.id.reglastname); // EditText for user's last name.
        phone = findViewById(R.id.regphone); // EditText for user phone number.
        password = findViewById(R.id.regpassword); // EditText for user password.
        btnregister = findViewById(R.id.btnregister); // Button to handle registration.
        btnlogin = findViewById(R.id.btnlogin); // Button to navigate back to the login screen.

        // Set up click listener for the registration button.
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input from registration fields.
                String strFirstname = firstname.getText().toString().trim();
                String strLastname = lastname.getText().toString().trim();
                String strEmail = email.getText().toString().trim();
                String strPassword = password.getText().toString().trim();
                String strPhone = phone.getText().toString().trim();

                // Check if any of the required fields are empty.
                if (strFirstname.isEmpty() || strLastname.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty() || strPhone.isEmpty()) {
                    Toast.makeText(register.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a `Users` object with the entered details.
                    User user = new User(strFirstname, strLastname, strEmail, strPassword, strPhone);

                    // Add the user to the database using the `dbConnect` helper.
                    db.addUser(user);

                    // Display success message and navigate back to the login screen.
                    Toast.makeText(register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        // (Optional) Add a click listener for `btnlogin` to navigate back to login screen directly.
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the main login activity.
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}