package com.example.textualbookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<Book> cartList;

    public CartAdapter(ArrayList<Book> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Book book = cartList.get(position);
        holder.imageViewBook.setImageResource(book.getImage());
        holder.textViewTitle.setText(book.getTitle());
        holder.textViewPrice.setText(String.format("$%.2f", book.getPrice()));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewBook;
        TextView textViewTitle;
        TextView textViewPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBook = itemView.findViewById(R.id.imageViewBook);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}