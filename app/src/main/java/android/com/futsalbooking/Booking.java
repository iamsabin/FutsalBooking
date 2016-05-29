package android.com.futsalbooking;

import android.com.futsalbooking.adapter.TabsBookingAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class Booking extends ActionBarActivity implements
        ActionBar.TabListener {
    private final String LOG_TAG = Booking.class.getSimpleName();
    private ViewPager viewPager;
    private TabsBookingAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        // Initialization
        try {
            viewPager = (ViewPager) findViewById(R.id.pager);
            actionBar = getSupportActionBar();

            mAdapter = new TabsBookingAdapter(getSupportFragmentManager());
            viewPager.setAdapter(mAdapter);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            // Adding Tabs
            for (String tab_name : tabs) {
                actionBar.addTab(
                        actionBar.newTab()
                                .setText(tab_name)
                                .setTabListener(this)
                );
            }
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "NullPointerException Found");
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected actionBar.setSelectedNavigationItem(position);
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

}