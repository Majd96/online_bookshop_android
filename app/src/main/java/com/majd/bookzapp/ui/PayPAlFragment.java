package com.majd.bookzapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.majd.bookzapp.R;
import com.majd.bookzapp.dataBase.BookContract;


public class PayPAlFragment extends Fragment {
    private View rootView;
    private Button login;

    public  PayPAlFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pay_pal, container, false);
        login=rootView.findViewById(R.id.button3);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getContentResolver().delete(BookContract.BookEntry.CONTENT_URI,
                        null,
                       null);
                Context ctx   = getActivity().getApplicationContext();
                 SharedPreferences sharedPreferences = ctx.getSharedPreferences("total", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("key",0);
                editor.commit();

                Intent intent=new Intent(getContext(),CheckOutActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(),"succeeded purchase\nnow you can track shipping process",Toast.LENGTH_LONG).show();
            }
        };
        login.setOnClickListener(listener);
        return rootView;
    }
}
