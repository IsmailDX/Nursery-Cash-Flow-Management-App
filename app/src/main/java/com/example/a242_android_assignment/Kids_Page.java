package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Kids_Page extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kids_page_layout);

        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();

        Button displayBtn = findViewById(R.id.displayKidsBtn);
        Button enrolBtn = findViewById(R.id.enrolKidBtn);
        Button chargeBtn = findViewById(R.id.chargeKidBtn);
        Button updateBtn = findViewById(R.id.updateKidBtn);
        Button unenrollBtn = findViewById(R.id.unenrollKidBtn);

        //Clicking on Icons
        displayBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i1 = new Intent(getApplicationContext(), DisplayKids_Page.class);
                startActivity(i1);
            }
        });

        enrolBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i2 = new Intent(getApplicationContext(), Enrol_Page.class);
                startActivity(i2);
            }
        });

        chargeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i3 = new Intent(getApplicationContext(), Charge_Page.class);
                startActivity(i3);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i4 = new Intent(getApplicationContext(), Update_Page.class);
                startActivity(i4);
            }
        });

        unenrollBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i5 = new Intent(getApplicationContext(), Unenroll_Page.class);
                startActivity(i5);
            }
        });


    } //onCreate

} //class