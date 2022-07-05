package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard_Page extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_page_layout);

        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();

    }

}