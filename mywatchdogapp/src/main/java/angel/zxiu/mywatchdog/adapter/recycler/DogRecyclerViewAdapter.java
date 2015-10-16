package angel.zxiu.mywatchdog.adapter.recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.holder._BaseRecyclerViewHolder;
import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.util.AudioUtil;

/**
 * Created by zxui on 14/10/15.
 */
public class DogRecyclerViewAdapter extends RecyclerView.Adapter<DogViewHolder> {
    Handler handler = new Handler();
    Context mContext;
    List<Dog> dogs;


    public DogRecyclerViewAdapter(Context context) {
        this.mContext = context;
        dogs = Dog.allDogs;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dog, parent, false);
        DogViewHolder viewHolder = new DogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, final int position) {
        final Dog dog = dogs.get(position);
        holder.bindValue(dog);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dog.setSelected(true);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }
}

class DogViewHolder extends _BaseRecyclerViewHolder<Dog> {
    View itemView;
    ImageView image;
    TextView textView;
    CheckBox checkBox;

    public DogViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @Override
    public void findViews(View itemView) {
        textView = (TextView) itemView.findViewById(R.id.text);
        image = (ImageView) itemView.findViewById(R.id.image);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
    }

    @Override
    public void bindValue(final Dog dog) {
        itemView.setSelected(dog.isSelected());
        textView.setText(dog.name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.performClick();
            }
        });
        checkBox.setChecked(dog.isSelected());
        image.setImageDrawable(dog.getLogoDrawable());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setView(View.inflate(v.getContext(), R.layout.item_barking, null));
//                builder.show();
                AudioUtil.play(dog.audioFilePaths.get(0));
            }
        });
    }
}


