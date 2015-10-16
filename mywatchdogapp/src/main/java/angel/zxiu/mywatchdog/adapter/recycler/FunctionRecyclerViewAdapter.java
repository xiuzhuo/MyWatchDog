package angel.zxiu.mywatchdog.adapter.recycler;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.object.Function;
import angel.zxiu.mywatchdog.holder._BaseRecyclerViewHolder;

/**
 * Created by zxui on 14/10/15.
 */
public class FunctionRecyclerViewAdapter extends RecyclerView.Adapter<FunctionViewHolder> {

    Context mContext;
    List<Function> mFunctions;


    public FunctionRecyclerViewAdapter(Context context,List<Function> functions) {
        this.mContext = context;
        mFunctions = functions;
    }

    @Override
    public FunctionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_function, null);
        FunctionViewHolder viewHolder = new FunctionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FunctionViewHolder holder, final int position) {
        final Function function = mFunctions.get(position);
        holder.bindValue(function);
    }

    @Override
    public int getItemCount() {
        return mFunctions.size();
    }
}

class FunctionViewHolder extends _BaseRecyclerViewHolder<Function> {
    View itemView;
    ImageView image;
    TextView textView;

    public FunctionViewHolder(View itemView) {
        super(itemView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void findViews(View itemView) {
        textView = (TextView) itemView.findViewById(R.id.text);
        image = (ImageView) itemView.findViewById(R.id.image);
    }

    @Override
    public void bindValue(Function func) {
        textView.setText(func.nameResId);
        image.setImageResource(func.imageResId);
    }
}


