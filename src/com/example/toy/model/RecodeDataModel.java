
package com.example.toy.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecodeDataModel {

    private String mMapping;
    private JSONArray mData;

    public String getMapping() {
        return mMapping;
    }

    public JSONArray getData() {
        return mData;
    }

    private RecodeDataModel() {
    }

    public static RecodeDataModel createFromJson(JSONObject obj) {
        RecodeDataModel model = new RecodeDataModel();
        try {
            model.mMapping = obj.getString("mapping");
            model.mData = obj.getJSONArray("data");
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
