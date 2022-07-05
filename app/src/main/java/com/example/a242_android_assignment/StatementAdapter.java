package com.example.a242_android_assignment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.google.android.material.textfield.TextInputEditText;

public class StatementAdapter extends CursorAdapter {

    public StatementAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.kid_statement, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextInputEditText kid_id =(TextInputEditText) view.findViewById(R.id.kid_id);
        TextInputEditText kid_full_name=(TextInputEditText) view.findViewById(R.id.kid_name);
        TextInputEditText kid_fees=(TextInputEditText) view.findViewById(R.id.kid_fees);

        String childId = cursor.getString(cursor.getColumnIndexOrThrow("kidId"));
        String childFirstName = cursor.getString(cursor.getColumnIndexOrThrow("firstName"));
        String childLastName = cursor.getString(cursor.getColumnIndexOrThrow("lastName"));
        String childFees = cursor.getString(cursor.getColumnIndexOrThrow("kidFees"));

        kid_id.setText(childId);
        kid_full_name.setText(childFirstName + " " + childLastName);
        kid_fees.setText(childFees);

    }

}