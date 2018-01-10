package com.majd.bookzapp.MVC.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by majd on 1/4/18.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    private static  int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserList.db.Users";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                        UserContract.UserEntry.COLUMN_FIRST_NAME + " TEXT, " +
                        UserContract.UserEntry.COLUMN_LAST_NAME + " TEXT, " +
                        UserContract.UserEntry.COLUMN_EMAIL + " TEXT, " +
                        UserContract.UserEntry.COLUMN_PASSWORD + " TEXT, " +
                        UserContract.UserEntry.COLUMN_BIRTH_DATE + " TEXT);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
        DATABASE_VERSION++;

    }
}
