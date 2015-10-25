package angel.zxiu.mywatchdog.fragment;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;

import angel.zxiu.mywatchdog.Constants;
import angel.zxiu.mywatchdog.R;

public class ScheduleFragment extends PreferenceFragmentCompat implements Constants {
    SwitchPreferenceCompat barkAutoPreference, barkInDayPreference, barkInNightPreference;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preference);
        findPreference();
        initPreference();
    }

    void findPreference(){
        barkAutoPreference = (SwitchPreferenceCompat) findPreference(KEY_BARK_AUTO);
        barkInDayPreference = (SwitchPreferenceCompat) findPreference(KEY_BARK_IN_DAY);
        barkInNightPreference = (SwitchPreferenceCompat) findPreference(KEY_BARK_IN_NIGHT);
    }

    void initPreference(){
//        barkInDayPreference.setEnabled(barkAutoPreference.isChecked());
//        barkInNightPreference.setEnabled(barkAutoPreference.isChecked());
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == barkAutoPreference) {
            barkInDayPreference.setChecked(barkAutoPreference.isChecked());
            barkInNightPreference.setChecked(barkAutoPreference.isChecked());
        } else if (preference == barkInDayPreference || preference == barkInNightPreference) {
            barkAutoPreference.setChecked(barkInDayPreference.isChecked() || barkInNightPreference.isChecked());
        }
        initPreference();
        return super.onPreferenceTreeClick(preference);
    }
}