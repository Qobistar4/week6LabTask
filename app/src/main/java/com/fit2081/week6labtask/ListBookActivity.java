package com.fit2081.week6labtask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ListBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_id,new BookListsFragment()).commit();

    }
}