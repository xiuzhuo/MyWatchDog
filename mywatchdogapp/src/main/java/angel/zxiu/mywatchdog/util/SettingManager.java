package angel.zxiu.mywatchdog.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import angel.zxiu.mywatchdog.App;
import angel.zxiu.mywatchdog.Constants;

/**
 * Created by zxui on 16/10/15.
 */
public class SettingManager implements Constants {
    static String KEY_SELECTED_DOG_NAME = "key_selected_dog_name";
    ;

    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.context);
    }

    public static String getSelectedDogName() {
        return getSharedPreferences().getString(KEY_SELECTED_DOG_NAME, null);
    }

    public static boolean setSelectedDogName(String name) {
        return getSharedPreferences().edit().putString(KEY_SELECTED_DOG_NAME, name).commit();
    }


    public static boolean isBarkAuto() {
        return getSharedPreferences().getBoolean(KEY_BARK_AUTO, true);
    }

    public static boolean setBarkAuto(boolean barking) {
        return getSharedPreferences().edit().putBoolean(KEY_BARK_AUTO, barking).commit();
    }

    public static boolean isBarkInDay() {
        return getSharedPreferences().getBoolean(KEY_BARK_IN_DAY, true);
    }

    public static boolean setBarkInDay(boolean barking) {
        return getSharedPreferences().edit().putBoolean(KEY_BARK_IN_DAY, barking).commit();
    }

    public static boolean isBarkInNight() {
        return getSharedPreferences().getBoolean(KEY_BARK_IN_NIGHT, true);
    }

    public static boolean setBarkInNight(boolean barking) {
        return getSharedPreferences().edit().putBoolean(KEY_BARK_IN_NIGHT, barking).commit();
    }
}
