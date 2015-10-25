package angel.zxiu.mywatchdog;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.receiver.MyWatchDogReceiver;
import angel.zxiu.mywatchdog.util.SettingManager;

/**
 * Created by zxui on 16/10/15.
 */
public class App extends Application implements Constants {
    public static Context context;
    public static Resources resources;
    public static AlarmManager alarmManager;


    @Override
    public void onCreate() {
        super.onCreate();
//        Dog.init();
        context = this;
        Configuration confTmp = new Configuration(getResources().getConfiguration());
        confTmp.locale = new Locale("en");
        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        resources = new Resources(getAssets(), metrics, confTmp);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    public static void scheduleBarkingAlarm() {
        Intent alarmIntent = new Intent(context, MyWatchDogReceiver.class).setAction(ACTION_BARKING);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (SettingManager.isBarkAuto()) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.currentThreadTimeMillis(), 60 * 60 * 1000, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
        }
    }

    public static void requestAudioRecordPermission(final Activity activity) {
        int hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);



        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
                new AlertDialog.Builder(activity)
                        .setMessage("get permission")
                        .setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
                            }
                        })
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.cancel, null)
                        .create()
                        .show();
            }
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, 0);

        }
    }

}
