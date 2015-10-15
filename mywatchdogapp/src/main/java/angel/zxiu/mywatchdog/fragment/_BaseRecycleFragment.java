package angel.zxiu.mywatchdog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.listener.OnRecyclerItemTouchListener;

/**
 * Created by zxui on 14/10/15.
 */
public abstract class _BaseRecycleFragment extends _BaseFragment {
    public static final String KEY_LAYOUT_TYPE = "layout_type";


    public static final int LAYOUT_TYPE_VERTICAL_LIST = 0;
    public static final int LAYOUT_TYPE_HORIZONTAL_LIST = 1;
    public static final int LAYOUT_TYPE_VERTICAL_GRID = 2;
    public static final int LAYOUT_TYPE_HORIZONTAL_GRID = 3;
    private static final int SPAN_COUNT = 2;

    protected int mLayoutType = LAYOUT_TYPE_VERTICAL_GRID;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.Adapter myRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recycler;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(getRecyclerViewId());
        if (getOnRecyclerItemTouchListener() != null) {
            mRecyclerView.addOnItemTouchListener(getOnRecyclerItemTouchListener());
        }
        myRecyclerViewAdapter = getRecycleViewAdapter();
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        System.out.println("getArguments()=" + getArguments());

        if (getArguments() != null) {
            mLayoutType = getArguments().getInt(KEY_LAYOUT_TYPE);
        }
        configRecyclerView();
    }

    public _BaseRecycleFragment setLayoutType(int layoutType) {
        mLayoutType = layoutType;
        getBundle().putInt(KEY_LAYOUT_TYPE, layoutType);
        return this;
    }

    protected int getRecyclerViewId() {
        return R.id.recycleView;
    }

    private void configRecyclerView() {
        switch (mLayoutType) {
            case LAYOUT_TYPE_VERTICAL_LIST:
                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                break;
            case LAYOUT_TYPE_HORIZONTAL_LIST:
                mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case LAYOUT_TYPE_VERTICAL_GRID:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
                break;
            case LAYOUT_TYPE_HORIZONTAL_GRID:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, GridLayoutManager.HORIZONTAL, false);
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    protected abstract RecyclerView.Adapter getRecycleViewAdapter();

    protected abstract OnRecyclerItemTouchListener getOnRecyclerItemTouchListener();
}
