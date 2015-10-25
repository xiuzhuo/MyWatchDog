package angel.zxiu.mywatchdog.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import angel.zxiu.mywatchdog.adapter.recycler.DogRecyclerViewAdapter;
import angel.zxiu.mywatchdog.listener.OnRecyclerItemTouchListener;
import angel.zxiu.mywatchdog.util.AudioUtil;

/**
 * Created by zxui on 16/10/15.
 */
public class DogTypeFragment extends _BaseRecycleFragment {

    @Override
    protected RecyclerView.Adapter getRecycleViewAdapter() {
        return new DogRecyclerViewAdapter(getContext());
    }

    @Override
    protected OnRecyclerItemTouchListener getOnRecyclerItemTouchListener() {
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
        AudioUtil.stop();
    }
}
