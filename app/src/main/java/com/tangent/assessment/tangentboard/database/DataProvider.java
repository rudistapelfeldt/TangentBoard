package com.tangent.assessment.tangentboard.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Rudi Stapelfeldt on 2017/08/15.
 */

public class DataProvider extends ContentProvider {

    private static final int LOGIN = 204;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private Cursor returnCursor;

    private Uri returnUri;

    private DatabaseHelper helper;
    static {

        sUriMatcher.addURI(
                DatabaseHelper.CONTENT_AUTHORITY,
                DatabaseHelper.TB_LOGIN_DATA,
                LOGIN
        );
    }

    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        helper = new DatabaseHelper(getContext());

        switch (sUriMatcher.match(uri)) {
            case LOGIN:
                returnCursor = helper.getReadableDatabase()
                        .query(DatabaseHelper.TB_LOGIN_DATA,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder
                        );
                if (getContext() != null) {
                    returnCursor.setNotificationUri(getContext().getContentResolver(), DatabaseHelper.LOGIN_CONTENT_URI);
                }
                break;
        }


        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            int _id = 0;

            switch (sUriMatcher.match(uri)) {
                case LOGIN:
                    _id = (int) db.insert(DatabaseHelper.TB_LOGIN_DATA,
                            null,
                            values);
                    if (_id > 0) returnUri = DatabaseHelper.buildLogInUri(_id);
                    else throw new SQLException("Failed to insert row into " + uri);
                    if (getContext() != null) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    break;
            }

            return returnUri;
        }finally{
            db.close();
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        try {

            int count = 0;
            switch (sUriMatcher.match(uri)) {
                case LOGIN:
                    count = db.delete(DatabaseHelper.TB_LOGIN_DATA, selection, selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return count;
        }finally {
            db.close();
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            int count = 0;
            switch (sUriMatcher.match(uri)) {
                case LOGIN:
                    count = db.update(DatabaseHelper.TB_LOGIN_DATA, values, selection, selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Uknown URI " + uri);
            }
            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return count;
        }finally{
            db.close();
        }
    }
}
