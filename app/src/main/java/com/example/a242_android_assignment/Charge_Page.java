package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Charge_Page extends AppCompatActivity
{
    private ArrayAdapter adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charge_page_layout);

        databaseHelper = new DatabaseHelper(this);
        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();


        AutoCompleteTextView firstName = findViewById(R.id.firstNameATV);
        firstName.setThreshold(1);

        AutoCompleteTextView lastName = findViewById(R.id.lastNameATV);
        lastName.setThreshold(1);

        AutoCompleteTextView kidId = findViewById(R.id.kidIdATV);
        kidId.setThreshold(1);

        Spinner dropdownHours = findViewById(R.id.numHoursSpinner);

        AutoCompleteTextView discountBox = findViewById(R.id.discountCode);
        discountBox.setThreshold(1);

        Button chargeKid = findViewById(R.id.chargeBtn);

        Cursor cursor = databaseHelper.getKidsData();

        ArrayList<String> kidFNames = new ArrayList<>();
        ArrayList<String> kidLNames = new ArrayList<>();
        ArrayList<String> kidIdList = new ArrayList<>();

        if(cursor!=null && cursor.getCount() > 0)
        {
            if (cursor.moveToFirst())
            {
                do {

                    kidFNames.add(cursor.getString(cursor.getColumnIndexOrThrow("firstName")));
                    kidLNames.add(cursor.getString(cursor.getColumnIndexOrThrow("lastName")));
                    kidIdList.add(cursor.getString(cursor.getColumnIndexOrThrow("kidId")));

                } while (cursor.moveToNext());
            }
        }

        ArrayList<String> discountList = new ArrayList<>();
        discountList.add("ABCD20");
        discountList.add("EFGH15");
        discountList.add("IJKL10");
        discountList.add("MNOP30");
        discountList.add("QRSTU25");
        discountList.add("VWXYZ10");

        final String[] discountPercentage = new String[1];


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kidFNames);
        firstName.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kidLNames);
        lastName.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, kidIdList);
        kidId.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, discountList);
        discountBox.setAdapter(adapter);

        //Hour dropdown
        ArrayList<String> hourItems = new ArrayList<>();
        hourItems.add(0, "Hours");
        hourItems.add("1");
        hourItems.add("2");
        hourItems.add("3");
        hourItems.add("4");
        hourItems.add("5");
        hourItems.add("6");
        hourItems.add("7");
        hourItems.add("8");
        hourItems.add("9");

        final String[] hourValue = new String[1];

        ArrayAdapter adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item_layout, hourItems);

        dropdownHours.setAdapter(adapter2);

        dropdownHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Hours"))
                {
                    hourValue[0] = "0";
                }
                else
                {
                    //Gets item when clicked on
                    String item = parent.getItemAtPosition(position).toString();
                    hourValue[0] = item;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                hourValue[0] = "0";
            }
        });

        //Charge button functionality
        chargeKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String id = kidId.getText().toString();
                String discount = discountBox.getText().toString();

                Cursor kidExists = databaseHelper.getSingleKid(id);

                if(discount.equals(""))
                {
                    discountPercentage[0] = "100";
                }
                else
                {
                    String stringPercentage = discount.substring(discount.length() - 2);
                    discountPercentage[0] = stringPercentage;
                }

                if(fName.equals("") || lName.equals("") || id.equals(""))
                {
                    Toast.makeText(getApplication(),"Please fill all fields.",Toast.LENGTH_SHORT).show();
                }
                else if(hourValue[0].equals("0"))
                {
                    Toast.makeText(getApplication(),"Please select number of hours.",Toast.LENGTH_SHORT).show();
                }
                else if(kidExists.getCount() == 0)
                {
                    Toast.makeText(getApplication(),"ID does not exist.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int hourlyRate = 65;        //Amount to pay per hour
                    int numHours = Integer.parseInt(hourValue[0]);
                    float numPercentage = Integer.parseInt(discountPercentage[0]);
                    float formulaPercentage = numPercentage / 100;

                    float priceWithoutDiscount = hourlyRate * numHours;

                    float finalPrice = priceWithoutDiscount - (priceWithoutDiscount * formulaPercentage);

                    if(numPercentage == 100)
                    {
                        databaseHelper.chargeKid(fName, lName, id, priceWithoutDiscount);
                    }
                    else
                    {
                        databaseHelper.chargeKid(fName, lName, id, finalPrice);
                    }

                    Toast.makeText(getApplication(),"Database updated.",Toast.LENGTH_SHORT).show();

                    firstName.setText("");
                    lastName.setText("");
                    kidId.setText("");
                    dropdownHours.setSelection(0);
                    discountBox.setText("");
                }
            }
        });


    } //onCreate

} //class