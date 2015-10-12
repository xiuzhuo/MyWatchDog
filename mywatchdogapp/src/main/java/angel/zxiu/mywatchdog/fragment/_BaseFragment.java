package angel.zxiu.mywatchdog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import angel.zxiu.mywatchdog.R;

/**
 * Created by zxui on 10/10/15.
 */
public class _BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static String DUMMY_TEXT = "DUMMY_TEXT";
    View view;
    TextView dummyTextView;
    String dummyText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dummyText = getArguments().getString(DUMMY_TEXT);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_base, null);
        dummyTextView = (TextView) view.findViewById(R.id.dummy_text);
        dummyTextView.setText(dummyText);
        return view;
    }

    @Override
    public void onRefresh() {

    }
}
