package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class DisplayKids_Page extends AppCompatActivity {

    Button display_kids_btn;
    LinearLayout display_kids_layout;
    ListView kidList;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaykids_page_layout);

        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment,bp).addToBackStack(null).commit();

        databaseHelper = new DatabaseHelper(this);

        kidList = (ListView) findViewById(R.id.kidList);
        display_kids_btn=(Button) findViewById(R.id.display_kids);
        display_kids_layout=(LinearLayout) findViewById(R.id.display_layout);


        display_kids_layout.setVisibility(View.GONE);
        Cursor kidCursor = databaseHelper.getKidsData();
        KidCursorAdapter kidAdapter = new KidCursorAdapter(this, kidCursor);
        kidList.setAdapter(kidAdapter);


        display_kids_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kidCursor.getCount()>0) {
                    display_kids_layout.setVisibility(View.VISIBLE);
                } else {
                    toastMessage("Database empty.");
                }
            }
        });


    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}