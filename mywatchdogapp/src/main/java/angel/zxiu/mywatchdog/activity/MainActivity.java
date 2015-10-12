package angel.zxiu.mywatchdog.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import angel.zxiu.mywatchdog.adapter.FragmentPagerAdapter;
import angel.zxiu.mywatchdog.fragment._BaseFragment;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentPagerAdapter;
    DrawerLayout mDrawerLayout;
    CoordinatorLayout mCoordinatorLayout;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    AppBarLayout mAppBarLayout;
    NavigationView mNavigationView;
    String[] mTitles;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        initViews();
        initData();
        configViews();
        Snackbar.make(mToolbar, "haha", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.mAppBarLayout);
        mNavigationView = (NavigationView) findViewById(R.id.mNavigationView);
    }

    void initData() {
        mTitles = getResources().getStringArray(R.array.tab_titles);
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putString(_BaseFragment.DUMMY_TEXT, mTitles[i]);
            _BaseFragment fragment = new _BaseFragment();
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }

    void configViews() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, android.R.string.ok, android.R.string.cancel);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
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
