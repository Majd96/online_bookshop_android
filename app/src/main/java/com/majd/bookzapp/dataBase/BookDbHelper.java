package com.majd.bookzapp.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by majd on 1/2/18.
 */

public class BookDbHelper extends SQLiteOpenHelper {
    private static  int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "bookList.db.books";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + BookContract.BookEntry.TABLE_NAME + " (" +
                        BookContract.BookEntry.COLUMN_ID + " TEXT, " +
                        BookContract.BookEntry.COLUMN_TITLE + " TEXT, " +
                        BookContract.BookEntry.COLUMN_DESCRIPTION + " TEXT, " +
                        BookContract.BookEntry.COLUMN_IMAGE_URL + " TEXT, " +
                        BookContract.BookEntry.COLUMN_PRICE + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BookContract.BookEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
        DATABASE_VERSION++;

    }
}
