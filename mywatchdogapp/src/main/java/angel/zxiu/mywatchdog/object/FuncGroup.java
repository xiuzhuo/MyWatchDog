package angel.zxiu.mywatchdog.object;

import java.util.ArrayList;
import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.activity.MotionDetectionActivity;
import angel.zxiu.mywatchdog.fragment.DogTypeFragment;
import angel.zxiu.mywatchdog.fragment.ScheduleFragment;
/**
 * Created by zxui on 16/10/15.
 */
public class FuncGroup {
    public static final List<FuncGroup> allGroups = new ArrayList<FuncGroup>() {
        {
            add(new FuncGroup(R.string.group_barking, false)
                    .addFunction(new Function(R.string.func_barking_now, R.drawable.logo_dog_barking, MotionDetectionActivity.class))
                    .addFunction(new Function(R.string.func_choose_dog, R.drawable.logo_choose_dog, DogTypeFragment.class))
                    .addFunction(new Function(R.string.func_schedule, R.drawable.logo_clock, ScheduleFragment.class)));

        }
    };

    public List<Function> functions = new ArrayList<>();
    public int nameResId;
    public boolean isRelease;

    public FuncGroup() {
    }

    public FuncGroup(int nameResId, boolean isRelease) {
        this.nameResId = nameResId;
        this.isRelease = isRelease;
    }

    public FuncGroup addFunction(Function f) {
        functions.add(f);
        return this;
    }

}
