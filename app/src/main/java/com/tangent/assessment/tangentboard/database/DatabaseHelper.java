package com.tangent.assessment.tangentboard.database;

import android.content.ContentUris;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by Rudi Stapelfeldt on 2017/10/04.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TANGENT.db";

    public static final String CONTENT_AUTHORITY = "com.tangent.assessment.tangentboard";

    public static final int DATABASE_VERSION = 2;

    public static final String CONTENT_KEY = "content";

    public static final String TB_LOGIN_DATA = "tb_login_data";

    public static final String TB_TOKEN_DATA = "tb_token_data";

    public static final String ID = "id";

    public static final String TOKEN = "token";

    public static final String EMAIL = "email";

    public static final String USERNAME = "username";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String IS_ACTIVE = "is_active";

    public static final String IS_STAFF = "is_staff";

    public static final String IS_SUPERUSER = "is_superuser";

    private static DatabaseHelper mInstance = null;


    //create table strings

    private static final String LOGIN_DATA_TABLE_CREATE =
            "CREATE TABLE " + TB_LOGIN_DATA + " (" +
                    ID + " TEXT, " +
                    USERNAME + " TEXT, " +
                    FIRST_NAME + " TEXT, " +
                    EMAIL + " TEXT, " +
                    LAST_NAME + " TEXT, " +
                    IS_ACTIVE + " BOOLEAN, " +
                    IS_STAFF + " BOOLEAN, " +
                    IS_SUPERUSER + " BOOLEAN " +
                    ")";

    private static final String TOKEN_DATA_TABLE_CREATE =
            "CREATE TABLE " + TB_TOKEN_DATA + " (" +
                    TOKEN + " TEXT " +
                    ")";

    //uri strings

    public static final Uri LOGIN_CONTENT_URI = new Uri.Builder()
            .scheme(CONTENT_KEY)
            .authority(CONTENT_AUTHORITY)
            .appendPath(TB_LOGIN_DATA)
            .build();

    public static final Uri TOKEN_CONTENT_URI = new Uri.Builder()
            .scheme(CONTENT_KEY)
            .authority(CONTENT_AUTHORITY)
            .appendPath(TB_TOKEN_DATA)
            .build();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LOGIN_DATA_TABLE_CREATE);
        sqLiteDatabase.execSQL(TOKEN_DATA_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_LOGIN_DATA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_TOKEN_DATA);
        onCreate(sqLiteDatabase);
    }

    public static DatabaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx);
        }
        return mInstance;
    }

    public static Uri buildLogInUri(int id) {
        return ContentUris.withAppendedId(LOGIN_CONTENT_URI, id);
    }

    public static Uri buildTokenUri(int id) {
        return ContentUris.withAppendedId(TOKEN_CONTENT_URI, id);
    }

}
