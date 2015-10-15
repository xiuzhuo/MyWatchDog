package angel.zxiu.mywatchdog.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import angel.zxiu.mywatchdog.adapter.recycler.DummyRecyclerViewAdapter;
import angel.zxiu.mywatchdog.listener.OnRecyclerItemTouchListener;

/**
 * Created by zxui on 10/10/15.
 */
public class DummyFragment extends _BaseRecycleFragment{
    @Override
    protected RecyclerView.Adapter getRecycleViewAdapter() {
        return new DummyRecyclerViewAdapter(getActivity());
    }

    @Override
    protected OnRecyclerItemTouchListener getOnRecyclerItemTouchListener() {
        return null;
    }
}
