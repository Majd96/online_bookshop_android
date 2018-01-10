package com.majd.bookzapp.fetchData;

import android.os.AsyncTask;
import android.util.Log;

import com.majd.bookzapp.Classes.Book;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by majd on 1/2/18.
 */

public class BookAsyncTask extends AsyncTask<Void, Void, ArrayList<Book>> {
    ArrayList<Book> articleArrayList;

    @Override
    protected ArrayList<Book> doInBackground(Void... voids) {

        URL url = FetchData.buildUrl();
        Log.d("koko", url + "");
        try {
            String jsonString = FetchData.getResponseFromHttpUrl(url);
            articleArrayList = FetchData.getJsonFromString(jsonString);
            return articleArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
