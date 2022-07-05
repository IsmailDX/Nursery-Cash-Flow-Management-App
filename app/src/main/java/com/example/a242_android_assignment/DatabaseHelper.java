package com.example.a242_android_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "Nursery";

    /*Kids Table*/
    private static final String kidTableName = "kidsTable";
    private static final String kidsId = "kidId";
    private static final String kidsFirstName = "firstName";
    private static final String kidsLastName = "lastName";
    private static final String kidsAge = "age";
    private static final String kidsGender = "gender";
    private static final String kidsAddress = "address";
    private static final String guardianName = "guardianName";
    private static final String guardianNumber = "guardianNumber";
    private static final String guardianEmail = "guardianEmail";
    private static final String guardianAddress = "guardianAddress";
    private static final String kidFees = "kidFees";
    private static final String createKidTable = "CREATE TABLE " + kidTableName + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "  + kidsId + " TEXT, "
            + kidsFirstName + " TEXT, " + kidsLastName + " TEXT, " + kidsAge + " TEXT, " + kidsGender + " TEXT, " + kidsAddress + " TEXT, " + guardianName + " TEXT, "
            + guardianNumber + " TEXT, " + guardianEmail + " TEXT, " + guardianAddress + " TEXT, " + kidFees + " DOUBLE )";


    /*Expenses Table*/
    private static final String expensesTableName = "expensesTable";
    private static final String expenseName = "expenseName";
    private static final String expenseAmount = "amount";
    private static final String createExpenseTable = "CREATE TABLE " + expensesTableName + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + expenseName + " TEXT, " + expenseAmount + " DOUBLE)";


    public DatabaseHelper(Context context){
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createKidTable);
        db.execSQL(createExpenseTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS ";
        db.execSQL(drop + kidTableName);
        db.execSQL(drop + expenseName);
        onCreate(db);
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM ";
        db.execSQL(delete + kidTableName);
        db.execSQL(delete + expenseName);
        onCreate(db);
    }

    /*methods for kid table*/

    public boolean addKid(Kid kid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kidsId,kid.getId());
        contentValues.put(kidsFirstName,kid.getFirstName());
        contentValues.put(kidsLastName,kid.getLastName());
        contentValues.put(kidsAge,kid.getAge());
        contentValues.put(kidsGender,kid.getGender());
        contentValues.put(kidsAddress,kid.getAddress());
        contentValues.put(guardianName,kid.getGuardianName());
        contentValues.put(guardianNumber,kid.getGuardianNumber());
        contentValues.put(guardianEmail,kid.getGuardianEmail());
        contentValues.put(guardianAddress, kid.getGuardianAddress());

        long result = db.insert(kidTableName, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteKid(String id, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(kidTableName, kidsId + " = ?" + " AND " + guardianNumber + " = ? ", new String[] {id, number});
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public boolean updateKid(Kid kid){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kidsId,kid.getId());
        contentValues.put(kidsFirstName,kid.getFirstName());
        contentValues.put(kidsLastName,kid.getLastName());
        contentValues.put(kidsAge,kid.getAge());
        contentValues.put(kidsGender,kid.getGender());
        contentValues.put(kidsAddress,kid.getAddress());
        contentValues.put(guardianName,kid.getGuardianName());
        contentValues.put(guardianNumber,kid.getGuardianNumber());
        contentValues.put(guardianEmail,kid.getGuardianEmail());
        contentValues.put(guardianAddress,kid.getGuardianAddress());

        long result = db.update(kidTableName, contentValues, kidsId + " = ?", new String[]{ String.valueOf(kid.getId())});

        if(result == -1){
            return false;
        } else {
            return true;
        }

    }

    public void chargeKid(String fName, String lName, String id, double fee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kidFees, fee);
        db.update(kidTableName, contentValues, kidsId + " = ?" + " AND " + kidsFirstName + " = ? " + " AND " + kidsLastName + " = ? " , new String[]{id, fName, lName});
    }

    public Cursor getKidsData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + kidTableName;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getSingleKid(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + kidTableName + " WHERE " + kidsId + " = '" + id + "'", null );
        return data;
    }

    /*for expense table*/

    public void addExpense(String name, double amount ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(expenseName,name);
        contentValues.put(expenseAmount,amount);
        db.insert(expensesTableName, null, contentValues);
    }

    public void updateExpense(String name,  double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(expenseAmount, amount);
        db.update(expensesTableName, contentValues, expenseName + " = ?" , new String[]{name});
    }


}