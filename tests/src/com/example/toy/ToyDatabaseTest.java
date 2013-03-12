
package com.example.toy;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.toy.database.ToyDatabaseHelper;
import com.example.toy.util.FileUtils;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ToyDatabaseTest extends AndroidTestCase {

    private static final String TAG = "ToyDatabaseTest";
    private ToyDatabaseHelper mToyDatabaseHelper;

    // {['serial':'51105386080','sort':'体育竞技','amount':139'distribute':5],

    @Override
    protected void setUp() throws Exception {
        mToyDatabaseHelper = ToyDatabaseHelper.getInstance(getContext());
    }

    public void RecordInsert() {
        try {
            mToyDatabaseHelper.deleteAllRecord();
            JSONArray array = FileUtils.getJSONArrayFromAssets(getContext(), "record.txt");
            assertNotNull(array);
            Log.i(TAG, "start insert");
            long start = System.currentTimeMillis();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                // RecodeModel model = RecodeModel.createFromJson(obj);
                // ContentValues values = new ContentValues();
                // values.put(RecordTable.COLUMN_SERIAL, model.getSerial());
                // values.put(RecordTable.COLUMN_AMOUNT, model.getAmount());
                // values.put(RecordTable.COLUMN_SORT, model.getSort());
                // values.put(RecordTable.COLUMN_DISTRIB,
                // model.getDistribute());
                // mToyDatabaseHelper.insertRecord(values);
            }
            long duration = System.currentTimeMillis() - start;
            Log.i(TAG, "duration:" + duration);
        } catch (JSONException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    public void testRecordInsertWithArray() {
        mToyDatabaseHelper.deleteAllRecord();
        JSONArray array = FileUtils.getJSONArrayFromAssets(getContext(), "record.txt");
        assertNotNull(array);
        Log.i(TAG, "start testRecordInsertWithArray");
        Log.i(TAG, "length:" + array.length());
        long start = System.currentTimeMillis();
        mToyDatabaseHelper.insertRecordWithJSONArray(array);
        long duration = System.currentTimeMillis() - start;
        Log.i(TAG, "duration:" + duration);
    }

    public void testOpt1RecordInsertWithArray() {
        mToyDatabaseHelper.deleteAllRecord();
        JSONArray array = FileUtils.getJSONArrayFromAssets(getContext(), "record_opt1.txt");
        assertNotNull(array);
        Log.i(TAG, "start testOpt1RecordInsertWithArray");
        Log.i(TAG, "length:" + array.length());
        long start = System.currentTimeMillis();
        mToyDatabaseHelper.insertRecordWithOpt1JSONArray(array);
        long duration = System.currentTimeMillis() - start;
        Log.i(TAG, "duration:" + duration);
    }

}
