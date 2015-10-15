package angel.zxiu.mywatchdog.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zxui on 12/10/15.
 */
public abstract class _BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    View itemView;
    public _BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        findViews(itemView);
    }

    public View getItemView(){
        return itemView;
    }

    public abstract void findViews(View itemView);

    public abstract void bindValue(T value);

}
