package angel.zxiu.mywatchdog.util;

import android.media.MediaPlayer;

import java.io.IOException;

import angel.zxiu.mywatchdog.App;
import angel.zxiu.mywatchdog.R;

/**
 * Created by zxui on 16/10/15.
 */
public class AudioUtil {
    static MediaPlayer mediaPlayer = new MediaPlayer();

    static String[] audioFilePaths = new String[0];

    public static void play(String... audioFilePaths) {
        AudioUtil.audioFilePaths = audioFilePaths;


        for (String path : audioFilePaths) {
            try {
                mediaPlayer.setDataSource(App.context.getAssets().openFd(path).getFileDescriptor());
//                mediaPlayer.prepare();
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//
//                    }
//                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void play(String audioFilePath) {
        System.out.println(audioFilePath);
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
            mediaPlayer.setDataSource(App.context.getAssets().openFd(audioFilePath).getFileDescriptor());

//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//
//                }
//            });
//            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
