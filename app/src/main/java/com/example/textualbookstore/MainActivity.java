package com.example.textualbookstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Log.d(TAG, "Initializing RecyclerView");
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Log.d(TAG, "Creating book list");
            bookList = new ArrayList<>();
            bookList.add(new Book("Rich Dad Poor Dad", R.drawable.book1, 19.99, "A personal finance classic that explores wealth-building strategies."));
            bookList.add(new Book("You Become What You Think", R.drawable.book2, 29.99, "A motivational book that delves into the power of thought."));
            bookList.add(new Book("Psychology Of Money", R.drawable.book3, 25.99, "A deep dive into how people think about money and finance."));
            bookList.add(new Book("Power", R.drawable.book4, 22.99, "A book about achieving and understanding personal power."));
            bookList.add(new Book("The Diary Of CEO", R.drawable.book5, 24.99, "Insights from the world of business and leadership."));
            bookList.add(new Book("The Mountain is You", R.drawable.book6, 20.99, "Transforming self-sabotage into personal growth."));
            bookList.add(new Book("7 Habits of Highly Effective People", R.drawable.book7, 18.99, "Principles for personal and professional success."));
            bookList.add(new Book("The Power Of Letting Go", R.drawable.book8, 21.99, "How to release and achieve happiness."));
            bookList.add(new Book("Stop Over Thinking", R.drawable.book9, 17.99, "A guide to overcoming mental clutter."));
            bookList.add(new Book("Think Like a Monk", R.drawable.book10, 23.99, "Lessons on purpose and mindfulness from a monk."));

            Log.d(TAG, "Initializing adapter");
            runOnUiThread(() -> {
                bookAdapter = new BookAdapter(bookList, book -> {
                    Log.d(TAG, "Book clicked: " + book.getTitle());
                    Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
                    intent.putExtra("title", book.getTitle());
                    intent.putExtra("image", book.getImage());
                    intent.putExtra("price", book.getPrice());
                    intent.putExtra("description", book.getDescription());
                    startActivity(intent);
                });
                recyclerView.setAdapter(bookAdapter);
            });
        } catch (Exception e) {
            Log.e(TAG, "Error initializing MainActivity", e);
            runOnUiThread(() -> Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show());
        }
    }
}