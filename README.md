# LoopViewPager

* based ViewPager v25.0.1
* old implementation is in legacy/ 
* sample is in sample/

## Set-up

```groovy
repositories {
    maven {
        url 'https://github.com/yanzm/LoopViewPager/raw/master/maven-repo'
    }
}

dependencies {
    compile 'net.yanzm:loopviewpager:25.0.1'
}
```

## Usage

```
<android.support.v4.view.LoopViewPager
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

```
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }
}
```
