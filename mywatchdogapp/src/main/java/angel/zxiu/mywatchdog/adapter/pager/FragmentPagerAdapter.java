package angel.zxiu.mywatchdog.adapter.pager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import angel.zxiu.mywatchdog.fragment._BaseFragment;

/**
 * Created by zxui on 10/10/15.
 */
public class FragmentPagerAdapter extends FragmentStatePagerAdapter {
    List<_BaseFragment> mFragments;

    public FragmentPagerAdapter(FragmentManager fm, List<_BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public _BaseFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getTitle();
    }

}
