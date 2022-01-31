package com.example.test2fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class goaldatabasemenu extends SQLiteOpenHelper {
    public goaldatabasemenu(@Nullable Context context) {
        super(context, "goaltabel.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table GoalTabel(id TEXT primary key,task TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists GoalTabel");
    }

    public void insertuserdata(String id, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("task", task);
        db.insert("GoalTabel", null, contentValues);
    }

    public void updateuserdata(String id, String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        Cursor cursor = db.rawQuery("Select * from GoalTabel where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            db.update("GoalTabel", contentValues, "id=?", new String[]{id});

    }}

    public void deleteuserdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from GoalTabel where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            db.delete("GoalTabel", "id=?", new String[]{id});

        }
    }
    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from GoalTabel", null);
        return cursor;
    }

}
