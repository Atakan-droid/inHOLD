package com.buruqo.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
//Created by Varol KALA and Atakan G��ER
public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Inventory.db";
    private static final int DATABASE_VERSION = 1;

    private     static final  String TABLE_NAME ="my_inventory";
    private     static final  String COLUMN_ID ="_id";
    private     static final  String COLUMN_TITLE ="item_title";
    private     static final  String COLUMN_AUTHOR ="item_author";
    private     static final  String COLUMN_NUMBER ="item_number";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                " CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AUTHOR + " TEXT, " +
                        COLUMN_NUMBER + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);

    }
    void addItem(String title,String author,int number){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_NUMBER,number);
        long result= db.insert(TABLE_NAME, null,cv);
        if(result==-1)
        {
            Toast.makeText(context,"��lem Ba�ar�s�z Oldu",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context,"Ba�ar�l� Bir �ekilde Eklendi",Toast.LENGTH_SHORT).show();

        }

        }
    Cursor readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){

            cursor =db.rawQuery(query, null);


        }
        return cursor;
    }
    void updateData(String row_id, String title, String author, String number){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_NUMBER,number);

        long result = db.update(TABLE_NAME, cv,"_id=?",new String[] {row_id});

        if(result == -1){
            Toast.makeText(context, "G�ncelleme Ba�ar�s�z", Toast.LENGTH_SHORT).show();


        }else{

            Toast.makeText(context, "G�ncelleme Ba�ar�l�", Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[] {row_id});
        if(result == -1){

            Toast.makeText(context,"Silme Ba�ar�s�z Oldu", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context,"Ba�ar�l� Bir �ekilde Silindi", Toast.LENGTH_SHORT).show();
        }


    }
    void deleteAllData(){

       SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL("DELETE FROM " + TABLE_NAME);


    }
}
