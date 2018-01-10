package com.majd.bookzapp.dataBase;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by majd on 1/2/18.
 */

public class BookContract {
    public final static String CONTENT_AUTHORITY = "com.majd.bookzapp";
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BOOK = "book";


    public static class BookEntry implements BaseColumns {

        public static final Uri CONTENT_URI =

                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOK).build();
        public static final String TABLE_NAME = "bookTable";


        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ID = "bookId";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_PRICE = "price";



        //function that build uri for an article depends on its id
        public static Uri buildBookUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }
}
