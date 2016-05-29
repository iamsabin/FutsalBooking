package android.com.futsalbooking.adapter;

import android.com.futsalbooking.DaysFragment.Friday;
import android.com.futsalbooking.DaysFragment.Monday;
import android.com.futsalbooking.DaysFragment.Saturday;
import android.com.futsalbooking.DaysFragment.Sunday;
import android.com.futsalbooking.DaysFragment.Thursday;
import android.com.futsalbooking.DaysFragment.Tuesday;
import android.com.futsalbooking.DaysFragment.Wednesday;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class TabsBookingAdapter extends FragmentPagerAdapter {
    public TabsBookingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new Sunday();
            case 1:
                return new Monday();
            case 2:
                return new Tuesday();
            case 3:
                return new Wednesday();
            case 4:
                return new Thursday();
            case 5:
                return new Friday();
            case 6:
                return new Saturday();
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 7;
    }
}

