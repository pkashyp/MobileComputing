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

public class MainActivity extends AppCompatActivity {

    // Declare UI components for user input and navigation.
    EditText edtemail, edtpassword; // Fields for user email and password input.
    Button loginbtn, registrationbtn; // Buttons for login and registration actions.

    // Declare database helper class instance for user validation and registration.
    dbConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Initialize database helper.
        db = new dbConnect(this);

        // Print all registered users (for debugging or logging purposes).
        db.printAllUsers();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Link UI components with their respective layout elements.
        edtemail = findViewById(R.id.email); // EditText for email input.
        edtpassword = findViewById(R.id.password); // EditText for password input.

        loginbtn = findViewById(R.id.login); // Button for login action.
        registrationbtn = findViewById(R.id.register); // Button for navigating to registration.

        // Set up click listener for login button.
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stremail = edtemail.getText().toString().trim(); // Retrieve email input.
                String strpassword = edtpassword.getText().toString().trim(); // Retrieve password input.

                // Check if any of the input fields are empty.
                if (stremail.isEmpty() || strpassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both email and password!", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate user credentials using the database helper class.
                    if (db.validateUser(stremail, strpassword)) { // Calls validateUser method in dbConnect.
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Navigate to the Dashboard activity upon successful login.
                        //This is the place we need to put the code to go to dashboard.
                         Intent intent = new Intent(MainActivity.this, Dashboard.class); // Replace Dashboard with your dashboard activity class.
                                            startActivity(intent);

                    } else {
                        // Show error message for invalid credentials.
                        Toast.makeText(MainActivity.this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up click listener for registration button.
        registrationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the registration activity.
                Intent i = new Intent(MainActivity.this, register.class); // Replace register with your registration activity class.
                startActivity(i);
            }
        });
    }
}