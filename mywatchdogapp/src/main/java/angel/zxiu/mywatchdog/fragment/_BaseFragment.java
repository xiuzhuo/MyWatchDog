package angel.zxiu.mywatchdog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zxui on 10/10/15.
 */
public abstract class _BaseFragment extends Fragment {
    public static final String KEY_TITLE = "key_title";
    protected View view;
    protected String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutResId(), null);
        return view;
    }

    View findViewById(int viewId) {
        return view.findViewById(viewId);
    }

    protected abstract int getLayoutResId();

    public String getTitle() {
        return title;
    }

    public _BaseFragment setTitle(String title) {
        this.title = title;
        getBundle().putString(KEY_TITLE, title);
        return this;
    }

    protected Bundle getBundle() {
        Bundle bundle = getArguments();
        if (bundle==null){
            bundle = new Bundle();
            setArguments(bundle);
        }
        return bundle;
    }

}
