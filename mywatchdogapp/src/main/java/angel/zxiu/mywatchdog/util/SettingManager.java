package angel.zxiu.mywatchdog.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import angel.zxiu.mywatchdog.App;

/**
 * Created by zxui on 16/10/15.
 */
public class SettingManager {
    static String KEY_SELECTED_DOG_NAME = "key_selected_dog_name";

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.context);
    }

    public static String getSelectedDogName() {
        return getSharedPreferences().getString(KEY_SELECTED_DOG_NAME, null);
    }

    public static boolean setSelectedDogName(String name) {
        return getSharedPreferences().edit().putString(KEY_SELECTED_DOG_NAME, name).commit();
    }
}
