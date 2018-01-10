package com.majd.bookzapp.fetchData;

import android.net.Uri;

import com.majd.bookzapp.Classes.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by majd on 1/2/18.
 */

public class FetchData {
    final static String BASE_URL = "http://it-ebooks-api.info/v1/search";
    public static String QUERY = "general";
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(QUERY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static ArrayList<Book> getJsonFromString(String jsonString) throws JSONException {
        String title, description, imageUrl, subTitle,bookId;


        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray booksArray = jsonObject.getJSONArray("Books");
        ArrayList<Book> booksList = new ArrayList<>();

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject bookData = booksArray.getJSONObject(i);

            bookId=bookData.getString("ID");
            title = bookData.getString("Title");
            description = bookData.getString("Description");
            imageUrl = bookData.getString("Image");

          // subTitle = bookData.getString("SubTitle");


            booksList.add(new Book(bookId, title,"", description, imageUrl));
            //Log.d("movie title" , moviesList.get(i).getTitle() );

        }
        return booksList;
    }

}
