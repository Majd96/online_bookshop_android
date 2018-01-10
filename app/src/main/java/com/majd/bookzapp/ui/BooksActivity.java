package com.majd.bookzapp.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.majd.bookzapp.Adapters.BookAdapter;
import com.majd.bookzapp.Classes.Book;
import com.majd.bookzapp.MVC.view.MainActivity;
import com.majd.bookzapp.R;
import com.majd.bookzapp.fetchData.BookAsyncTask;
import com.majd.bookzapp.fetchData.FetchData;
import com.majd.bookzapp.dataBase.BookContract;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BooksActivity extends AppCompatActivity implements BookAdapter.OnBookClickListener {
    private ArrayList<Book> books;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BookAdapter bookAdapter;
    private EditText search;
    private int sum=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("total", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("UserId", 0);
        Log.d("%%%%%%%%%%%%",userId+"");
        if(userId==9){

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }

        if (isNetworkAvailable()) {
            recyclerView = findViewById(R.id.rv);

            linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);

            try {
                books = new BookAsyncTask().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //Log.d( "**", books.get(0).getTitle());

            bookAdapter = new BookAdapter(books, this,this);
            recyclerView.setAdapter(bookAdapter);

        }


    }

    public void searchQuery(View view) {

        search=findViewById(R.id.query);
        String text =search.getText().toString();
        FetchData.QUERY=text;
        if (isNetworkAvailable()) {
            recyclerView = findViewById(R.id.rv);

                linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);

            try {
                books = new BookAsyncTask().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
           // Log.d( "**", books.get(0).getTitle());

            bookAdapter = new BookAdapter(books, this,this);
            recyclerView.setAdapter(bookAdapter);
        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onBookClick(int clickedItemIndex, View view) {
        view.setBackground(getResources().getDrawable(R.drawable.saved_book));
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("total", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("UserId", 0);
        if(userId==9){

            ContentValues cv = new ContentValues();
            cv.put(BookContract.BookEntry.COLUMN_ID, books.get(clickedItemIndex).getBookId()+"");
            cv.put(BookContract.BookEntry.COLUMN_TITLE, books.get(clickedItemIndex).getTitle());
            cv.put(BookContract.BookEntry.COLUMN_DESCRIPTION, books.get(clickedItemIndex).getDesc());
            cv.put(BookContract.BookEntry.COLUMN_IMAGE_URL,books.get(clickedItemIndex).getImageUrl());
            cv.put(BookContract.BookEntry.COLUMN_PRICE,clickedItemIndex+10+"");
            Uri uri = getContentResolver().insert(BookContract.BookEntry.CONTENT_URI, cv);
            sum+=clickedItemIndex+10;
            Context  ctx   = getApplicationContext();
          sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("key", sum);
            editor.commit();
            Toast.makeText(this, "book is Added", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this,"please sign in",Toast.LENGTH_LONG).show();
        }


    }

    public void checkout(View view) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("total", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("UserId", 0);
        if(userId==9){

            Intent intent=new Intent(this,CheckOutActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this,"please sign in",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.signOut:
            {
                Context  ctx   = getApplicationContext();
                SharedPreferences sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("UserId", 4);
                editor.commit();
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
            }

                break;

        }
        return true;
    }

}
