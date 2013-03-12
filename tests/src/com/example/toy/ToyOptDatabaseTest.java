
package com.example.toy;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.toy.database.ToyOptDatabaseHelper;
import com.example.toy.model.RecodeDataModel;
import com.example.toy.util.FileUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class ToyOptDatabaseTest extends AndroidTestCase {

    private static final String TAG = "ToyOptDatabaseTest";

    /**
     * 动作竞技 1 体育竞技 2 经营策略 3 休闲益智 4
     */
    public void testOptRecordInsertWithArray() {
        ToyApplication.init(getContext());
        ToyOptDatabaseHelper dbHelper = ToyOptDatabaseHelper.getInstance(getContext());
        dbHelper.deleteAllRecord();
        JSONObject obj = FileUtils.getJSONObjectFromAssets(getContext(), "record_opt2.txt");
        assertNotNull(obj);
        RecodeDataModel model = RecodeDataModel.createFromJson(obj);
        JSONArray array = model.getData();
        String mapping = model.getMapping();
        // store mapping data
        ToyApplication.setRecodeMapping(mapping);
        Log.i(TAG, "start testOptRecordInsertWithArray");
        long start = System.currentTimeMillis();
        dbHelper.insertRecordWithOpt1JSONArray(array);
        long duration = System.currentTimeMillis() - start;
        Log.i(TAG, "duration:" + duration);
    }

}
