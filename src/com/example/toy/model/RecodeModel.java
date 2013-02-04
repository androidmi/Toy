
package com.example.toy.model;

import org.json.JSONException;
import org.json.JSONObject;

public class RecodeModel {

    private String serial;
    private String sort;
    private int amount;
    private int distribute;

    public static RecodeModel createFromJson(JSONObject obj) {
        try {
            RecodeModel model = new RecodeModel();
            model.serial = obj.getString("serial");
            model.sort = obj.getString("sort");
            model.amount = obj.getInt("amount");
            model.distribute = obj.getInt("distribute");
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSerial() {
        return serial;
    }

    public String getSort() {
        return sort;
    }

    public int getAmount() {
        return amount;
    }

    public int getDistribute() {
        return distribute;
    }

}
