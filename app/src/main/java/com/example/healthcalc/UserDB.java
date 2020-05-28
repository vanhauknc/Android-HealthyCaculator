package com.example.healthcalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class UserDB {

    SQLiteDatabase database;
    DBHelper dbHelper;
    public UserDB(Context context){
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.getWritableDatabase();

        }catch (Exception ex){
            database = dbHelper.getReadableDatabase();
        }
    }


    public long them(User user){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_USERNAME,user.getUsername());
        values.put(DBHelper.COT_PASSWORD,user.getPassword());
        return database.insert(DBHelper.TEN_BANG_User,null,values);
    }

    public boolean checkuser(User user){

        String[] cot = {
                DBHelper.COT_USERNAME,
                DBHelper.COT_PASSWORD
        };
        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_User,cot,null,null,null,null,null);
        if(cursor != null){
            String tk = user.getUsername();
            String mk = user.getPassword();

            while (cursor.moveToNext()){
                if(cursor.getString(0).equals(tk)){
                    if(cursor.getString(1).equals(mk)){
                        return true;
                    }
                }


            }
        }
       return false;


    }

    public long doimatkhau(User user){

        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_PASSWORD,user.getPassword());

        return  database.update(DBHelper.TEN_BANG_User,values,DBHelper.COT_USERNAME+"="+ "'"+user.getUsername()+"'" ,null);
    }

    public long xoatk(User user){
        return database.delete(DBHelper.TEN_BANG_User,DBHelper.COT_USERNAME+"="+ "'"+user.getUsername()+"'",null);
    }






}
