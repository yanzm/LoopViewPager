package com.uphyca.android.loopviewpager;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements TabListener {

    LoopViewPager mPager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int pgaeNum = 2;
        
        mPager = (LoopViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyFragmentStatePagerAdapter(getFragmentManager(), pgaeNum));
//        pager.setAdapter(new MyPagerAdapter());
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for(int i = 0; i < pgaeNum; i++) {
            actionBar.addTab(actionBar.newTab().setText(i + "").setTabListener(this));
        }
    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        int mPageNum = 0;
        
        public MyFragmentStatePagerAdapter(FragmentManager fm, int pageNum) {
            super(fm);
            mPageNum = pageNum;
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mPageNum;
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        public MyPagerAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(container.getContext());
            tv.setTextSize(100);
            tv.setText(position + "");
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }
    }

    public static class SimpleFragment extends Fragment {

        public static SimpleFragment newInstance(int position) {
            Bundle args = new Bundle();
            args.putInt("position", position);
            SimpleFragment sf = new SimpleFragment();
            sf.setArguments(args);
            return sf;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            int position = getArguments().getInt("position");

            TextView tv = new TextView(inflater.getContext());
            tv.setTextSize(100);
            tv.setText(position + "");
            return tv;
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        int position = getActionBar().getSelectedNavigationIndex();
        mPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        
    }
}
