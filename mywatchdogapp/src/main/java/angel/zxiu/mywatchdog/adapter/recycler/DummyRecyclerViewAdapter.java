package angel.zxiu.mywatchdog.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.holder._BaseRecyclerViewHolder;

/**
 * Created by zxui on 14/10/15.
 */
public class DummyRecyclerViewAdapter extends RecyclerView.Adapter<DummyRecyclerViewHolder> {
    Context mContext;
    public List<String> mData;

    public DummyRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mData.add((char) i + "");
        }
    }

    @Override
    public DummyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_dummy, null);
        DummyRecyclerViewHolder viewHolder = new DummyRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DummyRecyclerViewHolder holder, int position) {
        holder.bindValue(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class DummyRecyclerViewHolder extends _BaseRecyclerViewHolder<String> {
    TextView textView;

    public DummyRecyclerViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    public void findViews(View itemView) {
        textView = (TextView) itemView.findViewById(R.id.text);
    }

    @Override
    public void bindValue(String value) {
        textView.setText(value);
    }
}
