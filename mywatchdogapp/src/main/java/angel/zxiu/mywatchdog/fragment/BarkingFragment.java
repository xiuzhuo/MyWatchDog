package angel.zxiu.mywatchdog.fragment;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.activity.MotionDetectionActivity;
import angel.zxiu.mywatchdog.adapter.recycler.FunctionRecyclerViewAdapter;
import angel.zxiu.mywatchdog.function.Function;
import angel.zxiu.mywatchdog.listener.OnRecyclerItemTouchListener;

/**
 * Created by zxui on 14/10/15.
 */
public class BarkingFragment extends _BaseFunctionFragment {
    static {
        Function.getFunctions(BarkingFragment.class).add(new Function(R.string.func_barking_now, R.drawable.logo_dog_barking, MotionDetectionActivity.class));
        Function.getFunctions(BarkingFragment.class).add(new Function(R.string.func_schedule, R.drawable.logo_clock, null));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutType(LAYOUT_TYPE_VERTICAL_GRID);
    }

    @Override
    public String getTitle() {
        return "Barking";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected RecyclerView.Adapter getRecycleViewAdapter() {
        return new FunctionRecyclerViewAdapter(this);
    }

    @Override
    protected OnRecyclerItemTouchListener getOnRecyclerItemTouchListener() {
        return new OnRecyclerItemTouchListener(getActivity(), new OnRecyclerItemTouchListener.OnFingerActionListener() {
            @Override
            public void onFingerMove(View view, int position) {
                Function function = getFunctions().get(position);
                if (function.isType(Service.class)) {

                } else if (function.isType(Activity.class)) {

                } else if (function.isType(Fragment.class) || function.isType(android.app.Fragment.class)) {

                }
                System.err.println(function);
            }

            @Override
            public void onFingerUp(View view, int position) {
                Function function = getFunctions().get(position);
                if (function.isType(Service.class)) {

                } else if (function.isType(Activity.class)) {
                    startActivity(new Intent(getActivity(),function.clazz));
                } else if (function.isType(Fragment.class) || function.isType(android.app.Fragment.class)) {

                }
            }
        });
    }

    @Override
    public List<Function> getFunctions() {
        return Function.getFunctions(getClass());
    }

}
