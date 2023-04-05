package com.fit2081.week6labtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import android.content.SharedPreferences;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity  /*implements TokenizerInterface*/ {

    public static final String BOOK_NAME = "BookName";
    public static final String BOOK_ISBN = "BookISBN";
    public static final String SP_NAME = "SPName";
    public static final String SP_ID = "SP ID";
    public static final String SP_ISBN = "SP ISBN";
    public static final String SP_AUTHOR = "SP Author";
    public static final String SP_DESCRIPTION = "SP Description";
    public static final String SP_PRICE = "SP Price";
    public static final String LIFE_CYCLE = "LifeCycle";
    EditText bookIDET, bookNameET, bookISBNET, bookMakerET, bookDesET, bookPriceET;
    SharedPreferences sharedPreferences;
    ArrayList<String> bookList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView myListView;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        drawerlayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());


        bookIDET    = findViewById(R.id.bookIDET);
        bookNameET  = findViewById(R.id.bookNameET);
        bookISBNET  = findViewById(R.id.bookISBNET);
        bookMakerET = findViewById(R.id.bookMakerET);
        bookDesET   = findViewById(R.id.bookDesET);
        bookPriceET = findViewById(R.id.BookPriceET);

        myListView =  findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookList);
        myListView.setAdapter(adapter);

        /*Log.d(LIFE_CYCLE,"onCreate");
         * */

        sharedPreferences = getPreferences(MODE_PRIVATE);

        String SPBookID = sharedPreferences.getString(SP_ID,"");
        String SPBookName = sharedPreferences.getString(SP_NAME,"");
        String SPBookISBN = sharedPreferences.getString(SP_ISBN,"");
        String SPBookMaker = sharedPreferences.getString(SP_AUTHOR,"");
        String SPBookDes = sharedPreferences.getString(SP_DESCRIPTION,"");
        float SPBookPrice = sharedPreferences.getFloat(SP_PRICE,0);
        DecimalFormat df = new DecimalFormat(".00");

        bookIDET.setText(SPBookID);
        bookNameET.setText(SPBookName);
        bookISBNET.setText(SPBookISBN);
        bookMakerET.setText(SPBookMaker);
        bookDesET.setText(SPBookDes);
        if (SPBookPrice == 0) {
            bookPriceET.setText("");
        } else {
            bookPriceET.setText(df.format(SPBookPrice));
        }
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookID = bookIDET.getText().toString();
                String bookName = bookNameET.getText().toString();
                String bookISBN = bookISBNET.getText().toString();
                String bookMaker = bookMakerET.getText().toString();
                String bookDes = bookDesET.getText().toString();
                float price = Float.parseFloat(bookPriceET.getText().toString());
                DecimalFormat df = new DecimalFormat(".00");

                Toast.makeText(MainActivity.this, "Book (" + bookName + ")" + " and the price (" + df.format(price) + ")", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(SP_ID,bookID);
                editor.putString(SP_NAME,bookName);
                editor.putString(SP_ISBN,bookISBN);
                editor.putString(SP_AUTHOR,bookMaker);
                editor.putString(SP_DESCRIPTION,bookDes);
                editor.putFloat (SP_PRICE,price);

                editor.apply();

                String bookListText = bookName + " | " + df.format(price);
                bookList.add(bookListText);

                adapter.notifyDataSetChanged();

            }
        });
        /* Request permissions to access SMS
         */
        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.SEND_SMS, android.Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));
        /* Second Method for SMS
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        SMSReceiver receiver = new SMSReceiver(this);
        registerReceiver(receiver,filter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.clear_field_menu_id) {
            bookIDET.setText("");
            bookNameET.setText("");
            bookISBNET.setText("");
            bookMakerET.setText("");
            bookDesET.setText("");
            bookPriceET.setText("");
        }
        else if (id == R.id.load_data_menu_id) {
            String SPBookID = sharedPreferences.getString(SP_ID,"");
            String SPBookName = sharedPreferences.getString(SP_NAME,"");
            String SPBookISBN = sharedPreferences.getString(SP_ISBN,"");
            String SPBookMaker = sharedPreferences.getString(SP_AUTHOR,"");
            String SPBookDes = sharedPreferences.getString(SP_DESCRIPTION,"");
            float SPBookPrice = sharedPreferences.getFloat(SP_PRICE,0);
            DecimalFormat df = new DecimalFormat(".00");

            bookIDET.setText(SPBookID);
            bookNameET.setText(SPBookName);
            bookISBNET.setText(SPBookISBN);
            bookMakerET.setText(SPBookMaker);
            bookDesET.setText(SPBookDes);
            bookPriceET.setText(df.format(SPBookPrice));

        }
        // tell the OS
        return true;

    }

    class MyBroadCastReceiver extends BroadcastReceiver {
        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */
            StringTokenizer sT = new StringTokenizer(msg, "|");
            String tknBookID    = sT.nextToken();
            String tknBookName  = sT.nextToken();
            String tknBookISBN  = sT.nextToken();
            String tknBookMaker = sT.nextToken();
            String tknBookDes   = sT.nextToken();
            float tknBookPrice  = Float.parseFloat(sT.nextToken());
            DecimalFormat df = new DecimalFormat(".00");
            /*
             * Now, its time to update the UI
             * */
            bookIDET.setText(tknBookID);
            bookNameET.setText(tknBookName);
            bookISBNET.setText(tknBookISBN);
            bookMakerET.setText(tknBookMaker);
            bookDesET.setText(tknBookDes);
            bookPriceET.setText(df.format(tknBookPrice));
        }}
    /*Second Method for SMS
    @Override
    public void sendData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        StringTokenizer sT = new StringTokenizer(data, "|");
        String tknBookID    = sT.nextToken();
        String tknBookName  = sT.nextToken();
        String tknBookISBN  = sT.nextToken();
        String tknBookMaker = sT.nextToken();
        String tknBookDes   = sT.nextToken();
        float tknBookPrice  = Float.parseFloat(sT.nextToken());
        DecimalFormat df = new DecimalFormat(".00");

        bookIDET.setText(tknBookID);
        bookNameET.setText(tknBookName);
        bookISBNET.setText(tknBookISBN);
        bookMakerET.setText(tknBookMaker);
        bookDesET.setText(tknBookDes);
        bookPriceET.setText(df.format(tknBookPrice));
       }*/
    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();

            if (id == R.id.add_books_menuNav_id) {
                String bookID = bookIDET.getText().toString();
                String bookName = bookNameET.getText().toString();
                String bookISBN = bookISBNET.getText().toString();
                String bookMaker = bookMakerET.getText().toString();
                String bookDes = bookDesET.getText().toString();
                float price = Float.parseFloat(bookPriceET.getText().toString());
                DecimalFormat df = new DecimalFormat(".00");

                Toast.makeText(MainActivity.this, "Book (" + bookName + ")" + " and the price (" + df.format(price) + ")", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(SP_ID,bookID);
                editor.putString(SP_NAME,bookName);
                editor.putString(SP_ISBN,bookISBN);
                editor.putString(SP_AUTHOR,bookMaker);
                editor.putString(SP_DESCRIPTION,bookDes);
                editor.putFloat (SP_PRICE,price);

                editor.apply();

                String bookListText = bookName + " | " + df.format(price);
                bookList.add(bookListText);

                adapter.notifyDataSetChanged();

            } else if (id == R.id.remove_last_book_menuNav_id) {

                bookList.remove(bookList.size() -1);
                adapter.notifyDataSetChanged();
            } else if (id == R.id.remove_all_books_menuNav_id) {
                bookList.clear();
                adapter.notifyDataSetChanged();
            }
            // close the drawer
            drawerlayout.closeDrawers();
            // tell the OS
            return true;
        }
    }
    public void handleReload(View v){

        String SPBookID = sharedPreferences.getString(SP_ID,"");
        String SPBookName = sharedPreferences.getString(SP_NAME,"");
        String SPBookISBN = sharedPreferences.getString(SP_ISBN,"");
        String SPBookMaker = sharedPreferences.getString(SP_AUTHOR,"");
        String SPBookDes = sharedPreferences.getString(SP_DESCRIPTION,"");
        float SPBookPrice = sharedPreferences.getFloat(SP_PRICE,0);
        DecimalFormat df = new DecimalFormat(".00");

        bookIDET.setText(SPBookID);
        bookNameET.setText(SPBookName);
        bookISBNET.setText(SPBookISBN);
        bookMakerET.setText(SPBookMaker);
        bookDesET.setText(SPBookDes);
        bookPriceET.setText(df.format(SPBookPrice));

    }
    public void handleClear(View random){

        bookIDET.setText("");
        bookNameET.setText("");
        bookISBNET.setText("");
        bookMakerET.setText("");
        bookDesET.setText("");
        bookPriceET.setText("");

    }
    public void handleSubmit(View v){

        String bookID = bookIDET.getText().toString();
        String bookName = bookNameET.getText().toString();
        String bookISBN = bookISBNET.getText().toString();
        String bookMaker = bookMakerET.getText().toString();
        String bookDes = bookDesET.getText().toString();
        float price = Float.parseFloat(bookPriceET.getText().toString());
        DecimalFormat df = new DecimalFormat(".00");

        Toast.makeText(this, "Book (" + bookName + ")" + " and the price (" + df.format(price) + ")", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SP_ID,bookID);
        editor.putString(SP_NAME,bookName);
        editor.putString(SP_ISBN,bookISBN);
        editor.putString(SP_AUTHOR,bookMaker);
        editor.putString(SP_DESCRIPTION,bookDes);
        editor.putFloat (SP_PRICE,price);

        editor.apply();
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        String BookNameETRestored = savedInstanceState.getString(BOOK_NAME);
        bookNameET.setText(BookNameETRestored);
        String bookISBNETRestored = savedInstanceState.getString(BOOK_ISBN);
        bookISBNET.setText(bookISBNETRestored);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //super.onSaveInstanceState(outState);
        String saveBookNameET = bookNameET.getText().toString();
        outState.putString(BOOK_NAME,saveBookNameET);
        String saveBookISBNET = bookISBNET.getText().toString();
        outState.putString(BOOK_ISBN,saveBookISBNET);}


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LIFE_CYCLE,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LIFE_CYCLE,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LIFE_CYCLE,"onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LIFE_CYCLE,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LIFE_CYCLE,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LIFE_CYCLE,"onDestroy");
    }

}