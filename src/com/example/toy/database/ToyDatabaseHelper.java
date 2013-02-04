
package com.example.toy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.toy.model.RecodeModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class ToyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = ToyDatabaseHelper.class.getName();

    private static final String NAME = "toy";
    private static final int VERSION = 1;

    public class RecordTable {
        public static final String TABLE_NAME = "record";
        public static final String COLUMN_SERIAL = "serial";
        public static final String COLUMN_SORT = "sort";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DISTRIB = "distrib";
    }

    private static ToyDatabaseHelper sInstance;

    public ToyDatabaseHelper(Context context) {
        super(context, NAME, new CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery,
                    String editTable,
                    SQLiteQuery query) {
                Log.i(TAG, "newCursor");
                return null;
            }
        }, VERSION);
    }

    public static ToyDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ToyDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public void insertRecord(ContentValues values) {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.insert(RecordTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(db);
        }
    }

    public void insertRecordWithJSONArray(JSONArray array) {
        if (array == null || array.length() == 0) {
            return;
        }
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.beginTransaction();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                RecodeModel mode = RecodeModel.createFromJson(obj);
                ContentValues values = new ContentValues();
                values.put(RecordTable.COLUMN_SERIAL, mode.getSerial());
                values.put(RecordTable.COLUMN_AMOUNT, mode.getAmount());
                values.put(RecordTable.COLUMN_SORT, mode.getSort());
                values.put(RecordTable.COLUMN_DISTRIB, mode.getDistribute());
                db.insert(RecordTable.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(db);
        }
    }

    public void deleteAllRecord() {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.delete(RecordTable.TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDatabase(db);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(RecordTable.TABLE_NAME);
        sb.append(" (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(RecordTable.COLUMN_SERIAL + " TEXT UNIQUE NOT NULL,");
        sb.append(RecordTable.COLUMN_SORT + " TEXT NOT NULL,");
        sb.append(RecordTable.COLUMN_AMOUNT + " INTEGER NOT NULL,");
        sb.append(RecordTable.COLUMN_DISTRIB + " INTEGER NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    private void closeDatabase(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO onUpgrade
    }
}
