package angel.zxiu.mywatchdog.object;

import android.graphics.drawable.Drawable;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import angel.zxiu.mywatchdog.App;
import angel.zxiu.mywatchdog.util.SettingManager;

/**
 * Created by zxui on 16/10/15.
 */
public class Dog {

    public static List<Dog> allDogs = new ArrayList<>();
    static final String KEY_AUDIO_PATH = "audio_path", KEY_IMAGE_PATH = "image_path", KEY_DOGS = "dogs", KEY_NAME = "name", KEY_IMAGE = "image", KEY_AUDIOS = "audios";
    static String AUDIO_PATH, IMAGE_PATH;

    public static void init() {
        try {
            String content = IOUtils.toString(App.context.getAssets().open("dogs.cfg"));
            System.out.println(content);
            JSONObject jsonObject = new JSONObject(content);
            AUDIO_PATH = jsonObject.getString(KEY_AUDIO_PATH);
            IMAGE_PATH = jsonObject.getString(KEY_IMAGE_PATH);
            JSONArray dogsJSONArray = jsonObject.getJSONArray(KEY_DOGS);
            for (int i = 0; i < dogsJSONArray.length(); i++) {
                JSONObject dogObj = dogsJSONArray.getJSONObject(i);
                Dog dog = new Dog(dogObj.getString(KEY_NAME));
                dog.logoFilePath = IMAGE_PATH + dogObj.getString(KEY_IMAGE);
                JSONArray audios = dogObj.getJSONArray(KEY_AUDIOS);
                for (int j = 0; j < audios.length(); j++) {
                    dog.audioFilePaths.add(AUDIO_PATH + audios.getString(j));
                }
                allDogs.add(dog);
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
