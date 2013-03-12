
package com.example.toy.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecodeModel {

    private String serial;
    private int sort;
    private int amount;
    private int distribute;

    private RecodeModel() {
    }

    /**
     * @param obj 
     *            [{'serial':'85538352166','sort':'动作竞技','amount':54,'distribute'
     *            : 0 } ,
     *            {'serial':'74722873748','sort':'体育竞技','amount':165,'distribute':1
     *            6 } , . . . ]
     * @return
     */
    @Deprecated
    public static RecodeModel createFromJson(JSONObject obj) {
        try {
            RecodeModel model = new RecodeModel();
            model.serial = obj.getString("serial");
            // model.sort = obj.getString("sort");
            model.amount = obj.getInt("amount");
            model.distribute = obj.getInt("distribute");
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final int SERIAL = 0;
    private static final int SORT = 1;
    private static final int AMOUNT = 2;
    private static final int DISTRIBUTE = 3;

    /**
     * @param array 
     *            [['85538352166','动作竞技',54,0],['74722873748','体育竞技',165,16],...
     *            ]
     * @return
     */
    public static RecodeModel createFromJson(JSONArray array) {
        try {
            RecodeModel model = new RecodeModel();
            model.serial = array.getString(SERIAL);
            model.sort = array.getInt(SORT);
            model.amount = array.getInt(AMOUNT);
            model.distribute = array.getInt(DISTRIBUTE);
            return model;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getSerial() {
        return serial;
    }

    public int getSort() {
        return sort;
    }

    public int getAmount() {
        return amount;
    }

    public int getDistribute() {
        return distribute;
    }

}
