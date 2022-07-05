package com.example.a242_android_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Statements_Page extends AppCompatActivity {

    //Kids
    Button All;
    Button One;
    ScrollView Scroll1;
    LinearLayout Scroll2;
    Button gen_btn;
    Button gen_btn2;
    LinearLayout statement_layout;
    LinearLayout statement_layoutAll;
    LinearLayout error_layout;
    EditText child_id;
    TextInputEditText one_kid_id;
    TextInputEditText one_kid_fullName;
    TextInputEditText one_kid_fees;
    LinearLayout IncomeP;
    ListView allStatements;


    //Cash Flow
    Button Expenses;
    Button Income;
    RelativeLayout R1;
    RelativeLayout Date;

    EditText Day;
    EditText Month;
    EditText Year;

    LinearLayout ExpensePage;
    Button Update;

    EditText Rent;
    EditText Utility;
    EditText Human;
    EditText Bus;
    EditText Employee;

    TextView MonthlyIn;
    TextView YearlyIn;

    EditText ADDone;
    EditText ADDtwo;
    EditText ADDthree;
    EditText NUMone;
    EditText NUMtwo;
    EditText NUMthree;
    Button Addbu;
    int i=0;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statements_page_layout);


        //Kids
        Scroll1= findViewById(R.id.ScrollView1);
        Scroll2= findViewById(R.id.ScrollView2);
        statement_layout=(LinearLayout) findViewById(R.id.statement_layout);
        statement_layoutAll=(LinearLayout) findViewById(R.id.statement_layoutALL);
        gen_btn=(Button) findViewById(R.id.generate_one_statement_btn);

        child_id=(EditText) findViewById(R.id.statement_child_id);
        error_layout=(LinearLayout) findViewById(R.id.else_error);
        one_kid_id =(TextInputEditText) findViewById(R.id.one_kid_id);
        one_kid_fullName=(TextInputEditText) findViewById(R.id.one_kid_fullName);
        one_kid_fees=(TextInputEditText) findViewById(R.id.one_kid_fees);
        statement_layout.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        Update=findViewById(R.id.UpdateButton);
        gen_btn2 =findViewById(R.id.generate_one_statement_btnALL);
        allStatements=findViewById(R.id.all_statements_list);


        //Cash Flow
        //Buttons
        All=findViewById(R.id.buttonAll);
        One=findViewById(R.id.buttonOne);
        Expenses=findViewById(R.id.buttonExpenses);
        Income=findViewById(R.id.buttonIncome);
        ExpensePage=findViewById(R.id.ExpensesPageXML);
        IncomeP=findViewById(R.id.IncomePage);

        //Info
        R1 = findViewById(R.id.IncomeNumbers);

        //EditTexts
        Rent=findViewById(R.id.RentNum);
        Utility=findViewById(R.id.UtilityNum);
        Human=findViewById(R.id.HumanNum);
        Bus=findViewById(R.id.BusNum);
        Employee=findViewById(R.id.EmployeeNum);
        MonthlyIn=findViewById(R.id.EXMonthly_Num);
        YearlyIn=findViewById(R.id.EXYearly_Num);

        ADDone=findViewById(R.id.ADD1);
        ADDtwo=findViewById(R.id.ADD2);
        ADDthree=findViewById(R.id.ADD3);
        NUMone=findViewById(R.id.NUM1);
        NUMtwo=findViewById(R.id.NUM2);
        NUMthree=findViewById(R.id.NUM3);
        Addbu=findViewById(R.id.AddButton);

        databaseHelper = new DatabaseHelper(this);

        databaseHelper.addExpense("Rent", 0);
        databaseHelper.addExpense("Utility Bills", 0);
        databaseHelper.addExpense("Human Resources", 0);
        databaseHelper.addExpense("Buses", 0);
        databaseHelper.addExpense("Employee Salaries", 0);

        //The 2 tabs code
        bottom_bar bp = new bottom_bar();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_bottom_fragment, bp).addToBackStack(null).commit();

        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);

        // setting up the tab host
        tabhost.setup();

        // Code for adding Tab 1 to the tabhost
        TabHost.TabSpec spec = tabhost.newTabSpec("Kids");
        spec.setContent(R.id.tab1);

        // setting the name of the tab 1
        spec.setIndicator("Kids");

        // adding the tab to tabhost
        tabhost.addTab(spec);

        // Code for adding Tab 2 to the tabhost
        spec = tabhost.newTabSpec("Cash Flow");
        spec.setContent(R.id.tab2);

        // setting the name of the tab 1
        spec.setIndicator("Cash Flow");
        tabhost.addTab(spec);

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            View v = tabhost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.white));
        }

        //Kids

        One.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                All.setBackgroundColor(Color.parseColor("#Cea2fd"));
                One.setBackgroundColor(Color.parseColor("#AA5AFF"));
                Scroll1.setVisibility(View.VISIBLE);
                Scroll2.setVisibility(View.GONE);
            }
        });

        All.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                All.setBackgroundColor(Color.parseColor("#AA5AFF"));
                One.setBackgroundColor(Color.parseColor("#Cea2fd"));
                Scroll2.setVisibility(View.VISIBLE);
                Scroll1.setVisibility(View.GONE);
            }
        });

        gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String test_id = "123456";

                Cursor cursorSingle = databaseHelper.getSingleKid(child_id.getText().toString());

                if(cursorSingle.getCount() < 1){
                    toastMessage("ID does not exist.");
                    statement_layout.setVisibility(View.GONE);
                    error_layout.setVisibility(View.VISIBLE);
                } else{
                    statement_layout.setVisibility(View.VISIBLE);

                    if(cursorSingle!=null && cursorSingle.getCount() > 0)
                    {
                        if (cursorSingle.moveToFirst())
                        {
                            do {

                                one_kid_id.setText(cursorSingle.getString(cursorSingle.getColumnIndexOrThrow("kidId")));
                                one_kid_fullName.setText(cursorSingle.getString(cursorSingle.getColumnIndexOrThrow("firstName")) +" " +cursorSingle.getString(cursorSingle.getColumnIndexOrThrow("lastName")) );
                                one_kid_fees.setText(cursorSingle.getString(cursorSingle.getColumnIndexOrThrow("kidFees")));

                            } while (cursorSingle.moveToNext());
                        }
                    }


                }

            }
        });


        statement_layoutAll.setVisibility(View.GONE);
        Cursor kidCursor = databaseHelper.getKidsData();
        StatementAdapter statementAdapter = new StatementAdapter(this, kidCursor);
        allStatements.setAdapter(statementAdapter);

        gen_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statement_layoutAll.setVisibility(View.VISIBLE);
            }
        });


        //Cash Flow Listeners
        Income.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Income.setBackgroundColor(Color.parseColor("#AA5AFF"));
                Expenses.setBackgroundColor(Color.parseColor("#Cea2fd"));
                R1.setVisibility(View.VISIBLE);
                IncomeP.setVisibility(View.VISIBLE);
                ExpensePage.setVisibility(View.GONE);

            }
        });

        Expenses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Income.setBackgroundColor(Color.parseColor("#Cea2fd"));
                Expenses.setBackgroundColor(Color.parseColor("#AA5AFF"));
                R1.setVisibility(View.INVISIBLE);
                ExpensePage.setVisibility(View.VISIBLE);
                IncomeP.setVisibility(View.GONE);
            }
        });

        Addbu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                i++;

                if (i==1){
                    ADDone.setVisibility(View.VISIBLE);
                    NUMone.setVisibility(View.VISIBLE);
                }
                else if (i==2){
                    ADDone.setVisibility(View.VISIBLE);
                    NUMone.setVisibility(View.VISIBLE);
                    ADDtwo.setVisibility(View.VISIBLE);
                    NUMtwo.setVisibility(View.VISIBLE);
                }
                else if (i==3){
                    ADDone.setVisibility(View.VISIBLE);
                    NUMone.setVisibility(View.VISIBLE);
                    ADDtwo.setVisibility(View.VISIBLE);
                    NUMtwo.setVisibility(View.VISIBLE);
                    ADDthree.setVisibility(View.VISIBLE);
                    NUMthree.setVisibility(View.VISIBLE);
                }
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Income.setBackgroundColor(Color.parseColor("#Cea2fd"));
                Expenses.setBackgroundColor(Color.parseColor("#AA5AFF"));
                R1.setVisibility(View.INVISIBLE);

                double Rent1 = Double.parseDouble(Rent.getText().toString());
                double Utility1 = Double.parseDouble(Utility.getText().toString());
                double Human1 = Double.parseDouble(Human.getText().toString());
                double Bus1 = Double.parseDouble(Bus.getText().toString());
                double Employee1 = Double.parseDouble(Employee.getText().toString());

                double Number1 = 0;
                double Number2 = 0;
                double Number3 = 0;

                int alreadyPresent1 = 0;
                int alreadyPresent2 = 0;
                int alreadyPresent3 = 0;



                if(!NUMone.getText().toString().isEmpty()) {
                    Number1 = Double.parseDouble(NUMone.getText().toString());

                    if(alreadyPresent1 == 0) {
                        databaseHelper.addExpense(ADDone.getText().toString(), Number1);
                        alreadyPresent1++;
                    }
                }

                if(!NUMtwo.getText().toString().isEmpty()) {
                    Number2 = Double.parseDouble(NUMtwo.getText().toString());

                    if(alreadyPresent2 == 0) {
                        databaseHelper.addExpense(ADDtwo.getText().toString(), Number2);
                        alreadyPresent2++;
                    }
                }

                if(!NUMthree.getText().toString().isEmpty()) {
                    Number3 = Double.parseDouble(NUMthree.getText().toString());

                    if(alreadyPresent3 == 0) {
                        databaseHelper.addExpense(ADDthree.getText().toString(), Number3);
                        alreadyPresent3++;
                    }

                }

                double Res = Rent1 + Utility1 + Human1 + Bus1 + Employee1 + Number1 + Number2 + Number3;
                double ResYear = Res * 12;

                databaseHelper.updateExpense("Rent", Rent1);
                databaseHelper.updateExpense("Utility Bills", Utility1);
                databaseHelper.updateExpense("Human Resources", Human1);
                databaseHelper.updateExpense("Buses", Bus1);
                databaseHelper.updateExpense("Employee Salaries", Employee1);
                databaseHelper.updateExpense(ADDone.getText().toString(), Number1);
                databaseHelper.updateExpense(ADDtwo.getText().toString(), Number2);
                databaseHelper.updateExpense(ADDthree.getText().toString(), Number3);

                MonthlyIn.setText((""+ Res + " AED"));
                YearlyIn.setText((""+ ResYear + " AED"));

            }
        });

    }

    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}