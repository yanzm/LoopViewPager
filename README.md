LoopViewPager
======================
ループできる ViewPager です。  
Support Package v4 の ViewPager の代わりに使います。

r11 の Suppor Package v4 の ViewPager のコードをベースに変更しています。
PagerAdapter を継承したアダプターの使い方は今まで通りです。
  
使い方
------
	<com.uphyca.android.loopviewpager.LoopViewPager 
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:id="@+id/pager"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent" />
	

	public class MainActivity extends Activity {

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	
	        LoopViewPager pager = (LoopViewPager) findViewById(R.id.pager);
	        pager.setAdapter(new MyFragmentStatePagerAdapter(getFragmentManager()));
	        pager.setAdapter(new MyPagerAdapter());
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
	}
 
