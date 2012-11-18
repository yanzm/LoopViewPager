package com.uphyca.android.loopviewpager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoopViewPager pager = (LoopViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter());
//        pager.setAdapter(new MyFragmentStatePagerAdapter(getFragmentManager()));
    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
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
