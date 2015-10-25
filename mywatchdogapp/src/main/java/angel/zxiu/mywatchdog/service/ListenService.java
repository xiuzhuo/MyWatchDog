package angel.zxiu.mywatchdog.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import java.security.Permission;

import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.util.AudioUtil;
import angel.zxiu.mywatchdog.util.RecordUtil;

/**
 * Created by xiu on 25.10.15.
 */
public class ListenService extends Service {
    RecordUtil.OnLevelChangeListener onLevelChangeListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        onLevelChangeListener = new RecordUtil.OnLevelChangeListener() {
            @Override
            public void onLevelChange(int level) {
                if (level > 90 && !AudioUtil.isPlaying()) {
                    AudioUtil.play(Dog.getSelectedDog().getRandomAudioFile());
                }
            }
        };
        RecordUtil.start();
        RecordUtil.addOnLevelChangeListener(onLevelChangeListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RecordUtil.removeOnLevelChangeListener(onLevelChangeListener);
        RecordUtil.stop();

    }
}
