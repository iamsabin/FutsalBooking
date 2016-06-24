package android.com.futsalbooking.adapter;

import android.com.futsalbooking.BookingActivity;
import android.com.futsalbooking.DailyBookingFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class TabsBookingAdapter extends FragmentPagerAdapter {
    private final String LOG_TAG = TabsBookingAdapter.class.getSimpleName();

    public TabsBookingAdapter(FragmentManager fm) {
        super(fm);
    }

    public static Long unixTimeStamp;

    @Override
    public Fragment getItem(int index) {
        try {
            BookingActivity booking = new BookingActivity();
            unixTimeStamp = booking.getDate();
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "null pointer exception in unixtimestamp");
        }
        try {
            switch (index) {
                case 0:
                    return DailyBookingFragment.newInstance(unixTimeStamp);
                // return DailyBookingFragment.newInstance(unixTimeStamp);
                case 1:
                    //return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000);
                case 2:
                    //return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000 * 2);
                case 3:
                    //return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000 * 3);
                case 4:
                    // return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000 * 4);
                case 5:
//                    return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000 * 5);
                case 6:
                    // return new DailyBookingFragment();

                    return DailyBookingFragment.newInstance(unixTimeStamp + 86400000 * 6);
            }
        } catch (NullPointerException e) {
            Log.v(LOG_TAG, "null pointer exception found");
        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 7;
    }
}

