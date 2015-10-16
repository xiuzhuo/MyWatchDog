package angel.zxiu.mywatchdog.fragment;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import angel.zxiu.mywatchdog.activity.FunctionActivity;
import angel.zxiu.mywatchdog.adapter.recycler.FunctionRecyclerViewAdapter;
import angel.zxiu.mywatchdog.listener.OnRecyclerItemTouchListener;
import angel.zxiu.mywatchdog.object.Function;
import angel.zxiu.mywatchdog.object.FuncGroup;

/**
 * Created by zxui on 14/10/15.
 */
public class FuncGroupFragment extends _BaseRecycleFragment {
    public FuncGroup funcGroup = new FuncGroup();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutType(LAYOUT_TYPE_VERTICAL_GRID);
    }

    public FuncGroupFragment setFuncGroup(FuncGroup funcGroup) {
        this.funcGroup = funcGroup;
        return this;
    }

    @Override
    protected RecyclerView.Adapter getRecycleViewAdapter() {
        return new FunctionRecyclerViewAdapter(getContext(), funcGroup.functions);
    }

    @Override
    protected OnRecyclerItemTouchListener getOnRecyclerItemTouchListener() {
        return new OnRecyclerItemTouchListener(getActivity(), new OnRecyclerItemTouchListener.OnFingerActionListener() {
            @Override
            public void onFingerMove(View view, int position) {
                Function func = funcGroup.functions.get(position);
                if (func.isType(Service.class)) {

                } else if (func.isType(Activity.class)) {

                } else if (func.isType(Fragment.class) || func.isType(android.app.Fragment.class)) {

                }
                System.err.println(func);
            }

            @Override
            public void onFingerUp(View view, int position) {
                Function func = funcGroup.functions.get(position);
                if (func.isType(Service.class)) {

                } else if (func.isType(Activity.class)) {
                    startActivity(new Intent(getContext(), func.clazz));
                } else if (func.isType(Fragment.class) || func.isType(android.app.Fragment.class)) {
                    Intent intent=new Intent(getContext(), FunctionActivity.class);
                    intent.putExtra("Function",func);
                    startActivity(intent);
                }
            }
        });
    }
}
