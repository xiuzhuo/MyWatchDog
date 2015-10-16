package angel.zxiu.mywatchdog.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.adapter.pager.FragmentPagerAdapter;
import angel.zxiu.mywatchdog.fragment.FuncGroupFragment;
import angel.zxiu.mywatchdog.fragment._BaseFragment;
import angel.zxiu.mywatchdog.fragment._BaseRecycleFragment;
import angel.zxiu.mywatchdog.object.Dog;
import angel.zxiu.mywatchdog.object.FuncGroup;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentPagerAdapter;
    DrawerLayout mDrawerLayout;
    CoordinatorLayout mCoordinatorLayout;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    AppBarLayout mAppBarLayout;
    NavigationView mNavigationView;
    List<_BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        initViews();
        initData();
        configViews();
        Snackbar.make(mToolbar, "haha", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

        System.err.println("dogs=" + Dog.allDogs.get(0));
    }

    void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mNavigationView = (NavigationView) findViewById(R.id.mNavigationView);

    }

    void initData() {
        mFragments = new ArrayList<>();
        for (FuncGroup funcGroup : FuncGroup.allGroups) {
//            Bundle bundle = new Bundle();
//            bundle.putInt(DummyFragment.KEY_LAYOUT_TYPE, DummyFragment.LAYOUT_TYPE_VERTICAL_LIST);
//            bundle.putString(DummyFragment.DUMMY_TEXT, mTitles[i]);
            _BaseFragment fragment = new FuncGroupFragment().setFuncGroup(funcGroup).setLayoutType(_BaseRecycleFragment.LAYOUT_TYPE_VERTICAL_GRID).setTitle(getString(funcGroup.nameResId));
            mFragments.add(fragment);
        }
    }

    void configViews() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, android.R.string.ok, android.R.string.cancel);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                System.out.println("mTitles="+mTitles[position]);
                getSupportActionBar().setTitle(mFragmentPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
