package angel.zxiu.mywatchdog.object;

import android.graphics.drawable.Drawable;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import angel.zxiu.mywatchdog.App;
import angel.zxiu.mywatchdog.util.SettingManager;

/**
 * Created by zxui on 16/10/15.
 */
public class Dog {

    public static final List<Dog> allDogs = new ArrayList<Dog>() {{
        try {
            String content = IOUtils.toString(App.context.getAssets().open("dogs.cfg"));
            System.out.println(content);

            for (String name : App.context.getAssets().list("dogs")) {
                Dog dog = new Dog(name);
                for (String logoFile : App.context.getAssets().list("dogs/" + name + "/logo")) {
                    dog.logoFilePath = "dogs/" + name + "/logo/" + logoFile;
                }
                for (String audioFile : App.context.getAssets().list("dogs/" + name + "/audio")) {
                    dog.audioFilePaths.add("dogs/" + name + "/audio/" + audioFile);
                }
                add(dog);
            }
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};
    static final String AUDIO_PATH = "audio_path", IMAGE_PATH = "image_path", DOGS = "DOGS", NAME = "name";
    static String audio_path, image_path;

    public static void init() {
        try {
            String content = IOUtils.toString(App.context.getAssets().open("dogs.cfg"));
            JSONObject jsonObject = new JSONObject(content);
            audio_path = jsonObject.getString(AUDIO_PATH);
            image_path = jsonObject.getString(IMAGE_PATH);
            JSONArray dogsJSONArray = jsonObject.getJSONArray(DOGS);
            for (int i = 0; i < dogsJSONArray.length(); i++) {
                JSONObject dogObj = dogsJSONArray.getJSONObject(i);
                Dog dog = new Dog(dogObj.getString(NAME));

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static Dog getSelectedDog() {
        Dog selectedDog = null;
        for (Dog dog : allDogs) {
            if (dog.isSelected()) {
                selectedDog = dog;
            }
        }
        if (selectedDog == null && allDogs.size() > 0) {
            selectedDog = allDogs.get(0);
            selectedDog.setSelected(true);
        }
        return selectedDog;
    }

    static {
        getSelectedDog();
    }

    public String name;
    public String logoFilePath;
    public List<String> audioFilePaths = new ArrayList<>();
    private List<String> randomAudioFilePaths = new ArrayList<>();
    Drawable drawable;

    public Dog(String name) {
        this.name = name;
    }

    public String getRandomAudioFile() {
        if (randomAudioFilePaths.size() == 0) {
            randomAudioFilePaths.addAll(audioFilePaths);
        }
        int size = randomAudioFilePaths.size();
        int index = (int) (Math.random() * size);
        String path = null;
        if (size > 0) {
            path = randomAudioFilePaths.get(index);
            randomAudioFilePaths.remove(index);
        }
        return path;
    }

    public Drawable getLogoDrawable() {
        if (drawable == null) {
            try {
                drawable = Drawable.createFromStream(App.context.getAssets().open(logoFilePath), name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

    public boolean isSelected() {
        return name.equals(SettingManager.getSelectedDogName());
    }

    public void setSelected(boolean selected) {
        SettingManager.setSelectedDogName(name);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", logoFilePath='" + logoFilePath + '\'' +
                ", audioFilePaths=" + audioFilePaths +
                '}';
    }
}
