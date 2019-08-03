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

    // Manga TABLE
    public static String TABLE_MANGA_NAME ="TABLE_PRODUCT";
    public static String MANGA_ID ="Id";
    public static String MANGA_NAME="Name";
    public static String MANGA_CODE="Code";
    public static String MAGA_CATEGORY="Category";
    //
    //
    /////////////////////////////////////////
    ////////////////////////////////////////
    //// QUERY TABLE
    ///////////////////////////////////////

//    public String CREATE_TABLE_PRODUCT= "CREATE TABLE IF NOT EXISTS" + TABLE_PRODUCT_NAME+" ( "+
//            PRODUCT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
//            PRODUCT_CODE+ " TEXT, "+
//            PRODUCT_CATEGORY+ " TEXT, "+
//            PRODUCT_NAME+ " TEXT )";




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
