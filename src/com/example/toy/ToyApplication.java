
package com.example.toy;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class ToyApplication extends Application {
    private static final String TAG = "ToyApplication";

    private static SharedPreferences mPrfe;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        init(this);
    }

    public static void init(Context context) {
        mPrfe = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public SharedPreferences getSharedPreferences() {
        return mPrfe;
    }

    public static void setRecodeMapping(String name) {
        String key = "recode_mapping";
        mPrfe.edit().putString(key, name).apply();
    }

    public static String getRecodeMapping() {
        String key = "recode_mapping";
        return mPrfe.getString(key, "");
    }
}
