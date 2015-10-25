package angel.zxiu.mywatchdog.util;

import android.media.MediaRecorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiu on 25.10.15.
 */
public class RecordUtil {
    public static MediaRecorder recorder;
    public static WatchThread thread;
    static List<OnLevelChangeListener> onLevelChangeListenerList = new ArrayList<>();

    public static void start() {
        stop();
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile("/dev/null");
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
            recorder.start();
            thread = new WatchThread() {
                @Override
                public void run() {
                    while (alive) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int level = (int) (Math.log(recorder.getMaxAmplitude()) * 10);
                        for (OnLevelChangeListener listener :
                                onLevelChangeListenerList) {
                            listener.onLevelChange(level);
                        }
                        System.out.println(level + " dB");
                    }

                }
            };
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (thread != null) {
            thread.alive = false;
            thread = null;
        }
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    public static void addOnLevelChangeListener(OnLevelChangeListener listener) {
        onLevelChangeListenerList.add(listener);
    }

    public static void removeOnLevelChangeListener(OnLevelChangeListener listener) {
        onLevelChangeListenerList.remove(listener);
    }


    static class WatchThread extends Thread {
        boolean alive = true;
    }

    public interface OnLevelChangeListener {
        public void onLevelChange(int level);
    }

}
