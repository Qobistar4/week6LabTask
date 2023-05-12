package com.fit2081.week6labtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.week6labtask.provider.Book;
import com.fit2081.week6labtask.provider.BookViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookListsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter adapter;
    private DrawerLayout drawerlayout;
    ArrayList<Book> bookList = new ArrayList<>();
    private BookViewModel bookViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookListsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookListsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookListsFragment newInstance(String param1, String param2) {
        BookListsFragment fragment = new BookListsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
        adapter = new MyAdapter(bookList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_lists, container, false);
        recyclerView = v.findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);
        // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
//        adapter = new MyAdapter(bookList);
        adapter = MainActivity.adapter;
        recyclerView.setAdapter(adapter);
        return v;
    }
}