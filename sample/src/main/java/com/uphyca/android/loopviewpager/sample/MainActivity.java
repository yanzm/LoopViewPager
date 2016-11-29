package com.uphyca.android.loopviewpager.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        final LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);

        final TabLayout.TabLayoutOnPageChangeListener pageChangeListener =
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewPager.addOnPageChangeListener(pageChangeListener);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        final int adapterCount = adapter.getCount();
        for (int i = 0; i < adapterCount; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(adapter.getPageTitle(i)), false);
        }

        if (adapterCount > 0) {
            final int curItem = viewPager.getCurrentItem();
            if (curItem != tabLayout.getSelectedTabPosition() && curItem < tabLayout.getTabCount()) {
                tabLayout.getTabAt(curItem).select();
            }
        }
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DummyFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }

    public static class DummyFragment extends Fragment {

        private static final String ARGS_POSITION = "position";

        @NonNull
        public static DummyFragment newInstance(int position) {
            final DummyFragment f = new DummyFragment();
            Bundle args = new Bundle();
            args.putInt(ARGS_POSITION, position);
            f.setArguments(args);
            return f;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_dummy, container, false);
            final TextView textView = (TextView) view.findViewById(R.id.text);
            textView.setText(String.valueOf(getArguments().getInt(ARGS_POSITION)));
            return view;
        }
    }
}
