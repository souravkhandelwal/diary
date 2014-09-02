package com.hackers.diary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DbManager {

    public static final String KEY_ID = "_id";
    public static final String KEY_START_TIME = "start_time";
    public static final String KEY_EVENT_TYPE = "event_type";
    public static final String KEY_EVENT_DATA = "event_data";
    private static final String DATABASE_CREATE =
            "create table events (" + KEY_ID + " integer primary key autoincrement, "
                    + KEY_START_TIME + " TIMESTAMP NOT NULL, "
                    + KEY_EVENT_TYPE + " integer NOT NULL,"
                    + KEY_EVENT_DATA + " string);";
    private static final String TAG = "DbManager";
    private static final String DB_NAME = "data";
    private static final String DB_TABLE = "events";
    private static final int DB_VERSION = 2;
    private final Context context;
    private SQLiteDatabase database;

    public DbManager(Context context) {
        this.context = context;
    }

    public DbManager open() throws SQLException {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public long createEvent(Date timestamp, int event_type, String event_data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_START_TIME, formatDate(timestamp));
        contentValues.put(KEY_EVENT_TYPE, event_type);
        contentValues.put(KEY_EVENT_DATA, event_data);

        return database.insert(DB_TABLE, null /* nullColumnHack */, contentValues);
    }

    public boolean deleteEvent(long id) {
        return database.delete(DB_TABLE, KEY_ID + "=" + id, null /* whereArgs */) > 0;
    }

    public Cursor fetchAllEvents() {
        return database.query(DB_TABLE,
                new String[]{KEY_ID, KEY_START_TIME, KEY_EVENT_TYPE, KEY_EVENT_DATA},
                null, null, null, null, null);
    }

    public Cursor fetchEvent(long rowId) throws SQLException {
        Cursor cursor = database.query(DB_TABLE,
                new String[]{KEY_ID, KEY_START_TIME, KEY_EVENT_TYPE, KEY_EVENT_DATA},
                KEY_ID + "=" + rowId,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateNote(long rowId, Date timestamp, int event_type, String event_data) {
        ContentValues args = new ContentValues();
        args.put(KEY_START_TIME, formatDate(timestamp));
        args.put(KEY_EVENT_TYPE, event_type);
        args.put(KEY_EVENT_DATA, event_data);

        return database.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }
}
