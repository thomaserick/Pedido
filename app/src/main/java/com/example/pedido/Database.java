package com.example.pedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pedido.db";

    private static final String CREATE_USER = "create table user (id integer primary key autoincrement, name TEXT, username TEXT, passwd TEXT);";

    private static final String DELETE_USER = "drop table if exists user";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_USER);

    }

    public boolean insertUser(String name, String user, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("username", user);
        cv.put("passwd", passwd);
        long id = db.insert("user", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }

    }

    //verifica user
    public boolean chkUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username from user where username=?", new String[]{user});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }

    public String validarLogin(String user, String passwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT username,passwd from user WHERE username=? and passwd=?", new String[]{user, passwd});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }


}
