package com.example.a242_android_assignment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.google.android.material.textfield.TextInputEditText;

public class KidCursorAdapter extends CursorAdapter {
    public KidCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.kid_item_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextInputEditText display_child_id =(TextInputEditText) view.findViewById(R.id.display_child_id);
        TextInputEditText display_child_first_name=(TextInputEditText) view.findViewById(R.id.display_child_first_name);
        TextInputEditText display_child_last_name=(TextInputEditText) view.findViewById(R.id.display_child_last_name);
        TextInputEditText display_child_age=(TextInputEditText)  view.findViewById(R.id.display_child_age);
        TextInputEditText display_child_gender=(TextInputEditText) view.findViewById(R.id.display_child_gender);

        String childId = cursor.getString(cursor.getColumnIndexOrThrow("kidId"));
        String childFirstName = cursor.getString(cursor.getColumnIndexOrThrow("firstName"));
        String childLastName = cursor.getString(cursor.getColumnIndexOrThrow("lastName"));
        String childAge = cursor.getString(cursor.getColumnIndexOrThrow("age"));
        String childGender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));

        display_child_id.setText(childId);
        display_child_first_name.setText(childFirstName);
        display_child_last_name.setText(childLastName);
        display_child_age.setText(childAge);
        display_child_gender.setText(childGender);

    }
}