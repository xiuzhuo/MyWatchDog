package angel.zxiu.mywatchdog.object;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import angel.zxiu.mywatchdog.App;
import angel.zxiu.mywatchdog.util.SettingManager;

/**
 * Created by zxui on 16/10/15.
 */
public class Dog {

    public static final List<Dog> allDogs = new ArrayList<Dog>() {{
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }};

    public static Dog  getSelectedDog(){
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
    static{
        getSelectedDog();
    }

    public String name;
    public String logoFilePath;
    public List<String> audioFilePaths = new ArrayList<>();
    Drawable drawable;

    public Dog(String name) {
        this.name = name;
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
