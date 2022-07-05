package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Account_Page extends AppCompatActivity
{

    TextInputEditText account_name;
    TextInputEditText account_email;
    TextInputEditText account_phone_number;
    TextInputEditText account_address;
    Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page_layout);

        account_name=(TextInputEditText) findViewById(R.id.account_name_2);
        account_email=(TextInputEditText) findViewById(R.id.account_email);
        account_phone_number=(TextInputEditText) findViewById(R.id.account_phone_number);
        account_address=(TextInputEditText) findViewById(R.id.account_address);
        logout_btn=(Button) findViewById(R.id.logout_btn);

        //Logout button
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplication(),"Logged out.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Account_Page.this, Login_Page.class));
            }
        });

    }
}