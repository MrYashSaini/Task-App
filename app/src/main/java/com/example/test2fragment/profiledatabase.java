package com.example.test2fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class profiledatabase extends SQLiteOpenHelper {
    public profiledatabase(@Nullable Context context) {
        super(context, "profiletabel.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ProfileTabel(id TEXT primary key,task TEXT,typepf TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists ProfileTabel");

    }
    public void insertuserdata(String id, String task,String typepf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("task", task);
        contentValues.put("typepf",typepf);
        db.insert("ProfileTabel", null, contentValues);
    }
public void deleteuserdata(String id) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor cursor = db.rawQuery("Select * from ProfileTabel where id=?", new String[]{id});
    if (cursor.getCount() > 0) {
        db.delete("ProfileTabel", "id=?", new String[]{id});
    }
}
public Cursor getdata() {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery("Select * from ProfileTabel", null);
    return cursor;
}


}
