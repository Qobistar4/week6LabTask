package com.fit2081.week6labtask;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.fit2081.week6labtask.provider.Book;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Book> bookList = new ArrayList<>();

    public MyAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBooks(List<Book> books) {
        this.bookList = books;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bookID, bookName, bookISBN, bookMaker, bookDes, bookPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookID = itemView.findViewById(R.id.item_ID);
            bookName = itemView.findViewById(R.id.item_Title);
            bookISBN = itemView.findViewById(R.id.item_ISBN);
            bookMaker = itemView.findViewById(R.id.item_Author);
            bookDes = itemView.findViewById(R.id.item_Desc);
            bookPrice = itemView.findViewById(R.id.item_Price);
        }

        public void bind(Book book) {
            DecimalFormat df = new DecimalFormat(".00");
            bookID.setText("Book ID: " + book.getRegisterID());
            bookName.setText("Book Title: " + book.getTitle());
            bookISBN.setText("Book ISBN: " + book.getIsbn());
            bookMaker.setText("Book Author: " + book.getAuthor());
            bookDes.setText("Book DESC: " + book.getDescription());
            bookPrice.setText("Book Price: " + df.format(book.getPrice()));
        }
    }
}
