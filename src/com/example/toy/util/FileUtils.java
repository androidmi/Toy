
package com.example.toy.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getAssertFileContent(Context context, String fileName) {
        InputStream in = null;
        try {
            in = context.getAssets().open(fileName);
            int available = in.available();
            byte[] buffer = new byte[available];
            in.read(buffer);
            String json = new String(buffer);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static JSONArray getJSONArrayFromAssets(Context context, String fileName) {
        try {
            return new JSONArray(getAssertFileContent(context, fileName));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSONObjectFromAssets(Context context, String fileName) {
        try {
            return new JSONObject(getAssertFileContent(context, fileName));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
