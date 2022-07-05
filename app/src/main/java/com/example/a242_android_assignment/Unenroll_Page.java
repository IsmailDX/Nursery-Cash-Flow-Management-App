package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Unenroll_Page extends AppCompatActivity {

    EditText child_id;
    EditText guardian_number;
    Button un_enrol_btn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unenroll_page_layout);

        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();

        databaseHelper = new DatabaseHelper(this);

        un_enrol_btn = (Button) findViewById(R.id.unEnrolButton);

        un_enrol_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                child_id = (EditText) findViewById(R.id.unEnrol_childId);

                guardian_number = (EditText) findViewById(R.id.unEnrol_guardianMobileNumber);

               if(child_id.getText().toString().equals("") || guardian_number.getText().toString().equals("")) {
                    toastMessage("Please enter data.");
               } else {
                    unenrolKid(child_id.getText().toString(), guardian_number.getText().toString());

                    child_id.setText("");
                    guardian_number.setText("");
               }

            }
        });

    }


    //Functions
    public void unenrolKid(String kidId, String guardianNumber){
        boolean insertData = databaseHelper.deleteKid(kidId, guardianNumber);

        if(insertData){
            toastMessage("Unenrolled successfully.");
        } else{
            toastMessage("Something went wrong.");
        }
    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}