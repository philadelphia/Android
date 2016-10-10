package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.TtsSpan;
import android.widget.Toast;

/**
 * Created by Tao.ZT.Zhang on 2016/9/13.
 */
public class MyDBHelper  extends SQLiteOpenHelper{
    private static MyDBHelper myDBHelper;
    private static int dbVersion = 2;
    private static String dbName = "app.db";
    public  synchronized static MyDBHelper getInstance(Context context){
        if (myDBHelper == null){
            return  myDBHelper = new MyDBHelper(context);
        }
    return  myDBHelper;
    }

    private final static String CREATE_DB  = "create table package( _id integer primary key autoincrement, "
            + "packageName text,"+ "packageVersion integer," + "packageVersionName text);";

    private Context context;
    private MyDBHelper(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
        Toast.makeText(context,"success to create db!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context,"success to upgrade db!", Toast.LENGTH_LONG).show();

    }
}
