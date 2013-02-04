
package com.example.toy;

import android.content.ContentValues;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.toy.database.ToyDatabaseHelper;
import com.example.toy.database.ToyDatabaseHelper.RecordTable;
import com.example.toy.model.RecodeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ToyDatabaseTest extends AndroidTestCase {

    private static final String TAG = "ToyDatabaseTest";
    private ToyDatabaseHelper mToyDatabaseHelper;

    // {['serial':'51105386080','sort':'体育竞技','amount':139'distribute':5],

    @Override
    protected void setUp() throws Exception {
        mToyDatabaseHelper = ToyDatabaseHelper.getInstance(getContext());
    }

    public void testRecordInsert() {
        try {
            mToyDatabaseHelper.deleteAllRecord();
            InputStream in = getContext().getAssets().open("record.txt");
            int available = in.available();
            byte[] buffer = new byte[available];
            in.read(buffer);
            String json = new String(buffer);
            JSONArray array = new JSONArray(json);
            Log.i(TAG, "start insert");
            long start = System.currentTimeMillis();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                RecodeModel model = RecodeModel.createFromJson(obj);
                ContentValues values = new ContentValues();
                values.put(RecordTable.COLUMN_SERIAL, model.getSerial());
                values.put(RecordTable.COLUMN_AMOUNT, model.getAmount());
                values.put(RecordTable.COLUMN_SORT, model.getSort());
                values.put(RecordTable.COLUMN_DISTRIB, model.getDistribute());
                mToyDatabaseHelper.insertRecord(values);
            }
            long duration = System.currentTimeMillis() - start;
            Log.i(TAG, "duration:" + duration);
        } catch (IOException e) {
            assertFalse(true);
            e.printStackTrace();
        } catch (JSONException e) {
            assertFalse(true);
            e.printStackTrace();
        }
    }

    public void testRecordInsertWithArray() {
        try {
            mToyDatabaseHelper.deleteAllRecord();
            InputStream in = getContext().getAssets().open("record.txt");
            int available = in.available();
            byte[] buffer = new byte[available];
            in.read(buffer);
            String json = new String(buffer);
            JSONArray array = new JSONArray(json);
            Log.i(TAG, "length:" + array.length());
            Log.i(TAG, "start insert");
            long start = System.currentTimeMillis();
            mToyDatabaseHelper.insertRecordWithJSONArray(array);
            long duration = System.currentTimeMillis() - start;
            Log.i(TAG, "duration:" + duration);
        } catch (IOException e) {
            assertFalse(true);
            e.printStackTrace();
        } catch (JSONException e) {
            assertFalse(true);
            e.printStackTrace();
        }
    }

}
