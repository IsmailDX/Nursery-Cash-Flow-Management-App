package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Enrol_Page extends AppCompatActivity {
    EditText child_id;
    EditText child_fname;
    EditText child_lname;
    EditText child_age;
    EditText child_gender;
    EditText child_address;
    EditText guardian_name;
    EditText guardian_number;
    EditText guardian_email;
    EditText guardian_address;
    Button enrol_btn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrol_page_layout);

        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment, bp).addToBackStack(null).commit();

        databaseHelper = new DatabaseHelper(this);
        enrol_btn = (Button) findViewById(R.id.enrolButton);

        enrol_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child_id = (EditText) findViewById(R.id.childId);
                child_fname = (EditText) findViewById(R.id.childFirstName);
                child_lname = (EditText) findViewById(R.id.childLastName);
                child_age = (EditText) findViewById(R.id.childAge);
                child_gender = (EditText) findViewById(R.id.childGender);
                child_address = (EditText) findViewById(R.id.childAddress);
                guardian_name = (EditText) findViewById(R.id.guardianName);
                guardian_number = (EditText) findViewById(R.id.guardianMobileNumber);
                guardian_email = (EditText) findViewById(R.id.guardianEmail);
                guardian_address = (EditText) findViewById(R.id.guardianAddress);

                String idChild = child_id.getText().toString();
                String fnameChild = child_id.getText().toString();
                String lnameChild = child_id.getText().toString();
                String ageChild = child_id.getText().toString();
                String genderChild = child_id.getText().toString();
                String guardNumChild = child_id.getText().toString();


                if(idChild.equals("") || fnameChild.equals("") || lnameChild.equals("") || ageChild.equals("") || genderChild.equals("") || guardNumChild.equals(""))
                {
                    Toast.makeText(getApplication(),"Please fill kid info.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Kid kid = new Kid(child_id.getText().toString(), child_fname.getText().toString(), child_lname.getText().toString(),
                            child_age.getText().toString(), child_gender.getText().toString(),  child_address.getText().toString() , guardian_name.getText().toString(),
                            guardian_number.getText().toString(), guardian_email.getText().toString(), guardian_address.getText().toString());

                    AddData(kid);
                }

                child_id.setText("");
                child_fname.setText("");
                child_lname.setText("");
                child_age.setText("");
                child_gender.setText("");
                child_address.setText("");
                guardian_name.setText("");
                guardian_number.setText("");
                guardian_email.setText("");
                guardian_address.setText("");

            }
        });

    }


    //Functions
    public void AddData(Kid kid){
        boolean insertData = databaseHelper.addKid(kid);
        if(insertData){
            toastMessage("Data entered successfully.");
        } else{
            toastMessage("Something went wrong.");
        }
    }


    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
