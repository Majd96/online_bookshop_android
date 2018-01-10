package com.majd.bookzapp.MVC.Model;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by majd on 1/4/18.
 */

public class UserContract {

    public final static String CONTENT_AUTHORITY = "com.majd.bookzapp.model";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USER = "user";


    public static class UserEntry implements BaseColumns {

        public static final Uri CONTENT_URI =

                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String TABLE_NAME = "userTable";


        public static final String COLUMN_FIRST_NAME = "firstName";
        public static final String COLUMN_LAST_NAME = "lastName";
        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_PASSWORD = "password";

        public static final String COLUMN_BIRTH_DATE = "birthDate";




        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }




}
