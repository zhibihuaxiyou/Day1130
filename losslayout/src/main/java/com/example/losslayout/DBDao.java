package com.example.losslayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * author：张腾
 * date：2018/11/30
 */
public class DBDao {

    private final SQLiteDatabase database;

    public DBDao(Context context){
        DBHelper dbHelper = new DBHelper(context, "why.db", null, 1);
        database = dbHelper.getWritableDatabase();
    }

    public long add(String data){
        ContentValues contentValues = new ContentValues();
        contentValues.put("data",data);
        return database.insert("why",null,contentValues);
    }

    public List<String> query(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.query("why", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex("data"));
            list.add(data);
        }
        return list;
    }

    public void delete(){
        database.delete("why",null,null);
    }

}
