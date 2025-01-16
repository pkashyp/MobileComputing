package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbConnect  extends SQLiteOpenHelper{

    // Database table and column names.
    private static String id = "id";
    private static String firstname = "firstname";
    private static String lastname = "lastname";
    private static String email = "email";
    private static String password = "password";
    private static String phone = "phone";

    // Database and table info.
    private static String dbName = "findmanager";
    private static String dbTable = "users";
    private static int dbVersion = 1;

    // Constructor to initialize the database connection.
    public dbConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    // Create the database and tables.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the `users` table with columns for user details.
        String query = "CREATE TABLE " + dbTable + " (" +
                id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                firstname + " TEXT, " +
                lastname + " TEXT, " +
                email + " TEXT, " +
                password + " TEXT, " +
                phone + " TEXT)";
        sqLiteDatabase.execSQL(query);

        // Insert mock data for testing purposes.
        sqLiteDatabase.execSQL("INSERT INTO " + dbTable +
                " (firstname, lastname, email, password, phone) VALUES " +
                "('Test', 'User', 'test@example.com', 'password123', '1234567890')");
    }

    // Handle database upgrade if needed.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(sqLiteDatabase);
    }

    // Method to add a user to the database.
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Insert user data into the `ContentValues` object.
        values.put("firstname", user.getFirstname());
        values.put("lastname", user.getLastname());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());

        // Insert the user data into the database.
        db.insert(dbTable, null, values);
        db.close();
    }

    // Method to validate user credentials during login.
    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Trim spaces from email and password.
        email = email.trim();
        password = password.trim();

        // Perform a case-insensitive comparison for email and exact password match.
        String query = "SELECT * FROM " + dbTable + " WHERE LOWER(email) = LOWER(?) AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email.toLowerCase(), password});

        // Check if a user with matching credentials is found.
        boolean isValid = cursor.getCount() > 0;

        // Close the cursor and database.
        cursor.close();
        db.close();

        return isValid;
    }

    // Method to print all users stored in the database (for debugging/testing).
    public void printAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTable;
        Cursor cursor = db.rawQuery(query, null);

        // Check if there are users and print their details.
        if (cursor.moveToFirst()) {
            do {
                String firstname = cursor.getString(cursor.getColumnIndexOrThrow("firstname"));
                String lastname = cursor.getString(cursor.getColumnIndexOrThrow("lastname"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

                System.out.println("User: " + firstname + " " + lastname + ", Email: " + email + " , password: " + password + ", Phone no: " + phone);
            } while (cursor.moveToNext());
        } else {
            System.out.println("No users found in the database.");
        }

        // Close the cursor and database.
        cursor.close();
        db.close();
    }
}
