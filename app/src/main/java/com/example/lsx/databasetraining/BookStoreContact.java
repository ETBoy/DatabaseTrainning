package com.example.lsx.databasetraining;

import android.provider.BaseColumns;

/**
 * Created by lsx on 2016/7/26.
 */
public class BookStoreContact {
    public abstract class BookContact implements BaseColumns{
        public static final String TABLE_NAME="Book";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_AUTHOR="author";
        public static final String COLUMN_NAME_PAGES="pages";
        public static final String COLUMN_NAME_PRICE="price";

    }
}
