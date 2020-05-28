package com.example.healthcalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TEN_DATABASE="HeathCaculator";

    public static final String TEN_BANG_User = "Users";

    public static final String COT_USERNAME = "username";
    public static final String COT_PASSWORD = "password";

    private static final String TAO_BANG_USER= ""
            + "create table " + TEN_BANG_User + " ( "
            + COT_USERNAME + " text primary key ,"
            + COT_PASSWORD + " text not null "
            + " );";


    public DBHelper(@Nullable Context context) {
        super(context, TEN_DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
