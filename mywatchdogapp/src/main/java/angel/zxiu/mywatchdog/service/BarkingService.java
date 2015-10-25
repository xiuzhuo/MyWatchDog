package angel.zxiu.mywatchdog.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.util.AudioUtil;

/**
 * Created by zxui on 15/10/15.
 */
public class BarkingService extends Service {

    private final IBinder mBinder=new MyBinder();

    public class MyBinder extends Binder{
        BarkingService getService(){
            return BarkingService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void bark(){

        AudioUtil.play(Dog.getSelectedDog().audioFilePaths.get(0));
    }


}
