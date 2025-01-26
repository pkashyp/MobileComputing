package com.example.textualbookstore;

import android.content.Intent; // Add this import
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookList;
    private ArrayList<Book> filteredBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText searchField = findViewById(R.id.searchField);

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

        filteredBookList = new ArrayList<>(bookList);
        bookAdapter = new BookAdapter(filteredBookList, book -> {
            Intent intent = new Intent(MainActivity.this, BookDetailsActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("image", book.getImage());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("description", book.getDescription());
            startActivity(intent);
        });
        recyclerView.setAdapter(bookAdapter);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterBooks(String query) {
        filteredBookList.clear();
        if (query.isEmpty()) {
            filteredBookList.addAll(bookList);
        } else {
            List<Book> filtered = bookList.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            filteredBookList.addAll(filtered);
        }
        bookAdapter.notifyDataSetChanged();
    }
}