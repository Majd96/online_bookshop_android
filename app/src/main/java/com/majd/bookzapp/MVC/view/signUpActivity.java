package com.majd.bookzapp.MVC.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.majd.bookzapp.MVC.Model.UserContract;
import com.majd.bookzapp.R;
import com.majd.bookzapp.ui.BooksActivity;

public class signUpActivity extends AppCompatActivity {
    EditText firstName;
    EditText lasttName;
    EditText email;
    EditText pass;
    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName=findViewById(R.id.fn);
        lasttName=findViewById(R.id.ln);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        date=findViewById(R.id.date);


    }

    public void google(View view) {
        Uri uri = Uri.parse("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void signup(View view) {
        ContentValues cv = new ContentValues();
        cv.put(UserContract.UserEntry.COLUMN_FIRST_NAME, firstName.getText().toString());
        cv.put(UserContract.UserEntry.COLUMN_LAST_NAME,lasttName.getText().toString());
        cv.put(UserContract.UserEntry.COLUMN_EMAIL,email.getText().toString());
        cv.put(UserContract.UserEntry.COLUMN_PASSWORD,pass.getText().toString());
        cv.put(UserContract.UserEntry.COLUMN_BIRTH_DATE,date.getText().toString());
        Uri uri = getContentResolver().insert(UserContract.UserEntry.CONTENT_URI, cv);

        Toast.makeText(this, "succeeded signing up", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this, BooksActivity.class);
        startActivity(intent);
        Context ctx   = getApplicationContext();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserId", 9);
        editor.commit();
    }
}
