package com.htccs.android.vkmusic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostDataBaseHelper extends SQLiteOpenHelper {

    public static final String TAG = PostDataBaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public PostDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_GUESTS_TABLE = "CREATE TABLE " + VkContract.PostTable.TABLE_NAME + " ("
                + VkContract.PostTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VkContract.PostTable.COLUMN_ID + " TEXT NOT NULL, "
                + VkContract.PostTable.COLUMN_NAME_GROUP + " TEXT NOT NULL, "
                + VkContract.PostTable.COLUMN_ICON + " TEXT NOT NULL, "
                + VkContract.PostTable.COLUMN_DATE + " TEXT NOT NULL, "
                + VkContract.PostTable.COLUMN_TEXT + " TEXT NOT NULL DEFAULT 0, "
                + VkContract.PostTable.COLUMN_IMAGE + " TEXT DEFAULT 0, "
                + VkContract.PostTable.COLUMN_LIKE + " INTEGER NOT NULL DEFAULT 0, "
                + VkContract.PostTable.COLUMN_REPOST + " INTEGER NOT NULL DEFAULT 0, "
                + VkContract.PostTable.COLUMN_COMMENT + " INTEGER NOT NULL DEFAULT 0, "
                + VkContract.PostTable.COLUMN_GROUP_ID + " TEXT NOT NULL);";

        String SQL_CREATE_GROUP_TABLE = "CREATE TABLE " + VkContract.GroupTable.TABLE_NAME + " ("
                + VkContract.GroupTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VkContract.GroupTable.COLUMN_NAME_GROUP + " TEXT NOT NULL, "
                + VkContract.GroupTable.COLUMN_ICON + " TEXT NOT NULL, "
                + VkContract.GroupTable.COLUMN_GROUP_ID + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_GROUP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GUESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VkContract.PostTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VkContract.GroupTable.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
