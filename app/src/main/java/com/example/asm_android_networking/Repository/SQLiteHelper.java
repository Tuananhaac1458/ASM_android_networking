package com.example.asm_android_networking.Repository;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
///////////////////Declare/////////////////////

    public static int DATABASE_VER= 1;
    public static String DATABASE_NAME ="MY_DATA";
    ///////////////////////////////////

    ///////////////Constructor/////////////////////
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
////////////////////////////////////////////////


    /////////////////////////////////////////
    ////////////////////////////////////////
    //// QUERY TABLE
    ///////////////////////////////////////






    //////////import interface void////////////////
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}
