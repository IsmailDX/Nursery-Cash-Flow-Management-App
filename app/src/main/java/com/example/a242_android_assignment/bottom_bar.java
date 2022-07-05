package com.example.a242_android_assignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class bottom_bar extends Fragment
{

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_bottom_bar,container,false);

        ImageView dashboardIcon = v.findViewById(R.id.Dashboard_Image);
        ImageView kidsIcon = v.findViewById(R.id.Kids_Image);
        ImageView statementsIcon = v.findViewById(R.id.Statements_Image);
        ImageView accountIcon = v.findViewById(R.id.Account_Image);

        //Clicking on Icons
        dashboardIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i1 = new Intent(getActivity(), Dashboard_Page.class);
                startActivity(i1);
            }
        });

        kidsIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i2 = new Intent(getActivity(), Kids_Page.class);
                startActivity(i2);
            }
        });

        statementsIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i3 = new Intent(getActivity(), Statements_Page.class);
                startActivity(i3);
            }
        });

        accountIcon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i4 = new Intent(getActivity(), Account_Page.class);
                startActivity(i4);
            }
        });

        return v;
    }


} //class
