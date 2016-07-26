package com.example.lsx.databasetraining;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
private Button mBtCreate;
    private Button mBtAdd;
    private Button mBtUpdate;
    private Button mBtDelete;
    private Button mBtSearch;
    private Button mBtTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtCreate= (Button) findViewById(R.id.activity_main_button_create);
        mBtCreate.setOnClickListener(this);
        mBtAdd= (Button) findViewById(R.id.activity_main_button_add);
        mBtAdd.setOnClickListener(this);
        mBtUpdate= (Button) findViewById(R.id.activity_main_button_update);
        mBtUpdate.setOnClickListener(this);
        mBtDelete= (Button) findViewById(R.id.activity_main_button_delete);
        mBtDelete.setOnClickListener(this);
        mBtSearch= (Button) findViewById(R.id.activity_main_button_search);
        mBtSearch.setOnClickListener(this);
        mBtTransaction= (Button) findViewById(R.id.activity_main_button_transaction);
        mBtTransaction.setOnClickListener(this);
    }
    private MyDatabaseHelper dbHelper;
@Override
    public void onClick(View view){
    SQLiteDatabase db;
    ContentValues values;
switch (view.getId()){
    case R.id.activity_main_button_create:
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);

        break;
    case R.id.activity_main_button_add:
        db=dbHelper.getWritableDatabase();
        values=new ContentValues();
        values.put(BookStoreContact.BookContact.TABLE_NAME,"The Da Vinci Code");
        values.put(BookStoreContact.BookContact.COLUMN_NAME_AUTHOR,"Dan Brown");
        values.put(BookStoreContact.BookContact.COLUMN_NAME_PAGES,454);
        values.put(BookStoreContact.BookContact.COLUMN_NAME_PRICE,16.96);
        db.insert(BookStoreContact.BookContact.TABLE_NAME,null,values);
        db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",new String[]{"The Lost Symbol","Dan Brown","510","19.95"});
        Log.d(TAG, "add record!");
        break;
    case R.id.activity_main_button_update:
        db=dbHelper.getWritableDatabase();
        values=new ContentValues();
        values.put("price",20);
        db.update("Book",values,"name=?",new String[]{"The Da Vinci Code"});
        Log.d(TAG, "update record!");
        break;
    case R.id.activity_main_button_delete:
        db=dbHelper.getWritableDatabase();
        db.delete("Book","pages>?",new String[]{"500"});
        Log.d(TAG, "delete record!");
        break;
    case R.id.activity_main_button_search:
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        if(cursor==null)
        {
            Log.d(TAG, "no record!");
            return;
        }
        while (cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex("name"));
            Log.d(TAG, "name:"+name);

        }
        cursor.close();

        break;
    case R.id.activity_main_button_transaction:
        db=dbHelper.getReadableDatabase();
        db.beginTransaction();
        try {
            db.delete("Book",null,null);
            if(true)
            {
                throw new NullPointerException();
            }
            values=new ContentValues();
            values.put("name","Game of Thrones");
            values.put("author","George Martin");
            values.put("price",20.85);
            values.put("pages",720);
            db.insert("Book",null,values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        break;
}


    }
}
