package com.majd.bookzapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.majd.bookzapp.Adapters.BookAdapter;
import com.majd.bookzapp.Adapters.savedBooksAdapter;
import com.majd.bookzapp.R;
import com.majd.bookzapp.dataBase.BookContract;

import static com.majd.bookzapp.Adapters.savedBooksAdapter.mCursor;

public class CheckOutActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,BookAdapter.OnBookClickListener {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private savedBooksAdapter adapter;
    private TextView totalPrice ;
    public static final int LOADER_ID = 13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        totalPrice=findViewById(R.id.sumPrice);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("total", Context.MODE_PRIVATE);

        int strSavedValue = sharedPreferences.getInt("key", 0);
        totalPrice.setText(strSavedValue+"$");
        recyclerView=findViewById(R.id.rv_saved);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new savedBooksAdapter(this,this);
        recyclerView.setAdapter(adapter);
       getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID:
                return new CursorLoader(this,
                        BookContract.BookEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);


        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public void realCheckout(View view) {
        Intent intent=new Intent(this,RealCheckOutActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBookClick(int clickedItemIndex, View view) {
        Cursor cursor = mCursor;
        cursor.moveToPosition(clickedItemIndex);
        String id = cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_ID));
      getContentResolver().delete(BookContract.BookEntry.CONTENT_URI,
                BookContract.BookEntry.COLUMN_ID + "=?",
                new String[]{id});


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("total", Context.MODE_PRIVATE);

        int strSavedValue = sharedPreferences.getInt("key", 0);
        String price=cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_PRICE));
        strSavedValue-=Integer.parseInt(price);
        Context  ctx   = getApplicationContext();
        //SharedPreferences sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", strSavedValue);
        editor.commit();
        totalPrice.setText(strSavedValue+"$");
        Toast.makeText(this, "book is deleted", Toast.LENGTH_LONG).show();
    }
}
