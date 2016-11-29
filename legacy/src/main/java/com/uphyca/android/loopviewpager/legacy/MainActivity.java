package com.uphyca.android.loopviewpager.legacy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private LoopViewPager pager;
//    ViewPager pager;

    private TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int pageNum = 3;

//        pager = (ViewPager) findViewById(R.id.pager);
        pager = (LoopViewPager) findViewById(R.id.pager);
        final MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), pageNum);
        pager.setAdapter(adapter);
        // pager.setAdapter(new MyPagerAdapter());

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(this);

        for (int i = 0; i < pageNum; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(i + ""));
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adapter.addPage();
                adapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adapter.removePage();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public float getPageWidth(int position) {
            // TODO Auto-generated method stub
            super.getPageWidth(position);
            if (position == 1) {
                return 0.78f;
            } else
                return 1.0f;
        }

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

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void addPage() {
            mPageNum++;
        }

        public void removePage() {
            if (mPageNum > 1) {
                mPageNum--;
            }
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

}
