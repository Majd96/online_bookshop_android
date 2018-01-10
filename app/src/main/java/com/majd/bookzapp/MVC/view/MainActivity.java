package com.majd.bookzapp.MVC.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.majd.bookzapp.MVC.Model.User;
import com.majd.bookzapp.MVC.Model.UserContract;
import com.majd.bookzapp.MVC.view.signUpActivity;
import com.majd.bookzapp.R;
import com.majd.bookzapp.ui.BooksActivity;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.signEmail);
        pass=findViewById(R.id.signPass);
    }

    public void signup(View view) {
        Intent intent=new Intent(this,signUpActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent=new Intent(this,BooksActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        String userEmail=email.getText().toString();
        String userPass=pass.getText().toString();

        Cursor cursor =getContentResolver().query(
                UserContract.UserEntry.CONTENT_URI,
                null,
                UserContract.UserEntry.COLUMN_EMAIL+ " = ? AND "+
                        UserContract.UserEntry.COLUMN_PASSWORD+ " = ?",
                new String[]{userEmail,userPass},
                null);
        if(cursor.getCount()>0){
            Toast.makeText(this,"succeeded signing in",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,BooksActivity.class);
            startActivity(intent);
            Context ctx   = getApplicationContext();
            SharedPreferences sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("UserId", 9);
            editor.commit();
        }
        else {
            Toast.makeText(this,"incorrect email or Password",Toast.LENGTH_LONG).show();

        }


    }
}
