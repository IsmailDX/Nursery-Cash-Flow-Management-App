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
import android.widget.Toast;

import java.util.ArrayList;

public class Update_Page extends AppCompatActivity {

    EditText update_child_id;
    EditText update_child_first_name;
    EditText update_child_last_name;
    EditText update_child_age;
    EditText update_child_gender;
    EditText update_child_address;
    EditText update_guardian_name;
    EditText update_guardian_number;
    EditText update_guardian_email;
    EditText update_guardian_address;
    Button update_btn;
    Button load_btn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_page_layout);

        databaseHelper = new DatabaseHelper(this);
        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();

        update_child_id=(EditText) findViewById(R.id.update_child_id);
        update_child_first_name=(EditText) findViewById(R.id.update_child_first_name);
        update_child_last_name=(EditText) findViewById(R.id.update_child_last_name);
        update_child_age=(EditText) findViewById(R.id.update_child_age);
        update_child_gender=(EditText) findViewById(R.id.update_child_gender);
        update_child_address=(EditText) findViewById(R.id.update_child_address);

        update_guardian_name=(EditText) findViewById(R.id.update_guardian_name);
        update_guardian_number=(EditText) findViewById(R.id.update_guardian_number);
        update_guardian_email=(EditText) findViewById(R.id.update_guardian_email);
        update_guardian_address=(EditText) findViewById(R.id.update_guardian_address);

        update_btn=(Button) findViewById(R.id.update_button);
        load_btn =(Button) findViewById(R.id.load_data);

        load_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseHelper.getSingleKid(update_child_id.getText().toString());

                if(cursor.getCount() < 1){
                    toastMessage("Student does not exist.");
                }

                if(cursor!=null && cursor.getCount() > 0)
                {
                    if (cursor.moveToFirst())
                    {
                        do {

                            update_child_first_name.setText(cursor.getString(cursor.getColumnIndexOrThrow("firstName")));
                            update_child_last_name.setText(cursor.getString(cursor.getColumnIndexOrThrow("lastName")));
                            update_child_age.setText(cursor.getString(cursor.getColumnIndexOrThrow("age")));
                            update_child_gender.setText(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
                            update_child_address.setText(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                            update_guardian_name.setText(cursor.getString(cursor.getColumnIndexOrThrow("guardianName")));
                            update_guardian_number.setText(cursor.getString(cursor.getColumnIndexOrThrow("guardianNumber")));
                            update_guardian_email.setText(cursor.getString(cursor.getColumnIndexOrThrow("guardianEmail")));
                            update_guardian_address.setText(cursor.getString(cursor.getColumnIndexOrThrow("guardianAddress")));

                        } while (cursor.moveToNext());
                    }
                }


            }
        });


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Kid kid = new Kid(update_child_id.getText().toString(), update_child_first_name.getText().toString(), update_child_last_name.getText().toString(),
                        update_child_age.getText().toString(), update_child_gender.getText().toString(),  update_child_address.getText().toString() , update_guardian_name.getText().toString(),
                        update_guardian_number.getText().toString(), update_guardian_email.getText().toString(), update_guardian_address.getText().toString());

                updateKid(kid);

                update_child_id.setText("");
                update_child_first_name.setText("");
                update_child_last_name.setText("");
                update_child_age.setText("");
                update_child_gender.setText("");
                update_child_address.setText("");
                update_guardian_name.setText("");
                update_guardian_number.setText("");
                update_guardian_email.setText("");
                update_guardian_address.setText("");

            }
        });

    }

    //Functions
    public void updateKid(Kid kid){

        boolean insertData = databaseHelper.updateKid(kid);
        if(insertData){
            toastMessage("Data updated successfully.");
        } else{
            toastMessage("Something went wrong.");
        }

    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}