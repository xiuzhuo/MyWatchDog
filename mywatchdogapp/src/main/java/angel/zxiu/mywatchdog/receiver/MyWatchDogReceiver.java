package angel.zxiu.mywatchdog.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import angel.zxiu.mywatchdog.Constants;
import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.service.BarkingService;
import angel.zxiu.mywatchdog.util.AudioUtil;
import angel.zxiu.mywatchdog.util.SettingManager;

/**
 * Created by xiu on 24.10.15.
 */
public class MyWatchDogReceiver extends BroadcastReceiver implements Constants{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case Intent.ACTION_BOOT_COMPLETED:
                if (SettingManager.isBarkAuto()) {
                    context.startService(new Intent(context, BarkingService.class));
                }
                break;
            case ACTION_BARKING:
                System.err.println(ACTION_BARKING);
                if (!AudioUtil.isPlaying()){
                    AudioUtil.play(Dog.getSelectedDog().getRandomAudioFile());
                }
                break;
        }
    }
}
