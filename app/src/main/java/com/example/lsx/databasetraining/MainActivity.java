package com.example.lsx.databasetraining;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);
    }
    private MyDatabaseHelper dbHelper;
    public void button2Clcik(View v){
        dbHelper.getWritableDatabase();
        Toast.makeText(MainActivity.this, "Create table book succeeded.", Toast.LENGTH_SHORT).show();
    }

    public void btAddClick(View v){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name","The Da Vinci Code");
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.96);
        db.insert("Book",null,values);
        db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",new String[]{"The Lost Symbol","Dan Brown","510","19.95"});


    }
    public void btUpdate(View v){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("price",20);
        db.update("Book",values,"name=?",new String[]{"The Da Vinci Code"});
    }
    public void btDelete(View v){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("Book","pages>?",new String[]{"500"});
    }
}
