package angel.zxiu.mywatchdog;

import android.app.Application;
import android.content.Context;

/**
 * Created by zxui on 16/10/15.
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
