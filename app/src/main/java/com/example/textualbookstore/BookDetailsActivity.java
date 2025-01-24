package com.example.textualbookstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        ImageView imageViewBookDetail = findViewById(R.id.imageViewBookDetail);
        TextView textViewBookDetailTitle = findViewById(R.id.textViewBookDetailTitle);
        TextView textViewBookDetailPrice = findViewById(R.id.textViewBookDetailPrice);
        TextView textViewBookDetailDescription = findViewById(R.id.textViewBookDetailDescription);
        Button buttonGoBack = findViewById(R.id.buttonGoBack);

        // Get data from intent
        String title = getIntent().getStringExtra("title");
        int image = getIntent().getIntExtra("image", 0);
        double price = getIntent().getDoubleExtra("price", 0.0);
        String description = getIntent().getStringExtra("description");

        // Set data to views
        imageViewBookDetail.setImageResource(image);
        textViewBookDetailTitle.setText(title);
        textViewBookDetailPrice.setText(String.valueOf(price));
        textViewBookDetailDescription.setText(description);

        buttonGoBack.setOnClickListener(v -> finish());
    }
}