package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_layout);

        EditText username = findViewById(R.id.inputUsernameET);
        EditText password = findViewById(R.id.inputPasswordET);
        Button login = findViewById(R.id.loginBtn);

        String storedUsername = "admin";
        String storedPassword = "adminpassword";

        //Login button functionality
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if(usernameValue.equals(storedUsername) && passwordValue.equals(storedPassword))
                {
                    Toast.makeText(getApplication(),"Login successful.",Toast.LENGTH_SHORT).show();

                    Intent i1 = new Intent(getApplicationContext(), Dashboard_Page.class);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(getApplication(),"Incorrect details entered.",Toast.LENGTH_SHORT).show();
                }

                username.setText("");
                password.setText("");

            }
        });


    } //onCreate

} //class