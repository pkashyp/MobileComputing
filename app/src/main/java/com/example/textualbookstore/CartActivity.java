package com.example.textualbookstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private ArrayList<Book> cartList;
    private TextView totalPrice;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalPrice);
        checkoutButton = findViewById(R.id.checkoutButton);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartList = new ArrayList<>();
        // Add books to cartList (this should be dynamic in a real app)
        cartList.add(new Book("Rich Dad Poor Dad", R.drawable.book1, 19.99, "A personal finance classic that explores wealth-building strategies."));
        cartList.add(new Book("You Become What You Think", R.drawable.book2, 29.99, "A motivational book that delves into the power of thought."));

        cartAdapter = new CartAdapter(cartList);
        cartRecyclerView.setAdapter(cartAdapter);

        calculateTotalPrice();

        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void calculateTotalPrice() {
        double total = 0;
        for (Book book : cartList) {
            total += book.getPrice();
        }
        totalPrice.setText(String.format("Total: $%.2f", total));
    }
}