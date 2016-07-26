package com.example.lsx.databasetraining;

/**
 * Created by lsx on 2016/7/26.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lsx on 2016/7/24.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK="create table book("
            +"id integer primary key autoincrement,author text,price real,pages integer,name text)";
    public static final String CREATE_CATEGORY="Create table Category(id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version){
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Table is Creating.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion)
        {
            case 1:
                db.execSQL(CREATE_CATEGORY);
            default:

        }
    }
}
