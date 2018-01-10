package com.majd.bookzapp.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.majd.bookzapp.Adapters.ViewPagerAdapter;
import com.majd.bookzapp.R;

public class RealCheckOutActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CardFragment cardFragment;
    PayPAlFragment payPAlFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_check_out);
        cardFragment=new CardFragment();
        payPAlFragment=new PayPAlFragment();


        toolbar = (Toolbar) findViewById(R.id.toolbar_checkout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("check out");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }



    private void setupViewPager(ViewPager viewPager) {
        //defines the number of tabs by setting up the appropriate fragments and tab name
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(cardFragment, "credit card");
        adapter.addFragment(payPAlFragment, "PayPal");
        viewPager.setAdapter(adapter);
    }


}
