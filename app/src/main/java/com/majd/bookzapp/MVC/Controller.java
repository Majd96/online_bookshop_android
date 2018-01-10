package com.majd.bookzapp.MVC;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.majd.bookzapp.MVC.Model.UserContract;
import com.majd.bookzapp.MVC.Model.UserDBHelper;

/**
 * Created by majd on 1/4/18.
 */

public  class Controller extends ContentProvider {

    //declare an integers ti define the uri's for the queries that we want to made
    private static final int USER = 100;
    private static final int USER_ID = 101;

    //declare the database that the content provider want to access
    private UserDBHelper mUserDbHelper;
    //declare the uriMatcher
    private final static UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        mUserDbHelper = new UserDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mUserDbHelper.getReadableDatabase();
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case USER:
                retCursor = db.query(UserContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case USER_ID:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(UserContract.UserEntry.TABLE_NAME,
                        projection,
                        UserContract.UserEntry._ID + "=?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    //build the uri matcher to match each uri to ies integers
    public static UriMatcher buildUriMatcher() {
        String content = UserContract.CONTENT_AUTHORITY;
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, UserContract.PATH_USER, USER);
        matcher.addURI(content, UserContract.PATH_USER + "/#", USER_ID);
        return matcher;

    }



    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mUserDbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case USER: {
                long _id = db.insert(UserContract.UserEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    returnUri = UserContract.UserEntry.buildUserUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mUserDbHelper.getWritableDatabase();
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if (null == selection) selection = "1";
        switch (sUriMatcher.match(uri)) {
            case USER:
                rowsDeleted = db.delete(
                        UserContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }


}
