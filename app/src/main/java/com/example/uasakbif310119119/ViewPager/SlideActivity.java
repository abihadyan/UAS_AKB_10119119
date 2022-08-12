package com.example.uasakbif310119119.ViewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uasakbif310119119.Login.LoginActivity;
import com.example.uasakbif310119119.Login.RegisterActivity;
import com.example.uasakbif310119119.MainActivity;
import com.example.uasakbif310119119.R;

public class SlideActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }

    public void onClick(View view){
        Intent intent = new Intent(SlideActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}