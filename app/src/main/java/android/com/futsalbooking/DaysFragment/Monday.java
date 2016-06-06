package android.com.futsalbooking.DaysFragment;

import android.com.futsalbooking.DailyBooking;
import android.com.futsalbooking.R;
import android.com.futsalbooking.adapter.DailyBookingAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class Monday extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_days_booking, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_daily_booking);

        DailyBooking[] dailyBooking = {
                new DailyBooking(R.drawable.booking_double, "8-9", "UserName"),
                new DailyBooking(R.drawable.booking_open, "9-10", "Nouser"),
                new DailyBooking(R.drawable.booking_playing, "10-11", "Play"),
                new DailyBooking(R.drawable.booking_single, "11-12", "Single")};

        DailyBookingAdapter dailyBookingAdapter = new DailyBookingAdapter(this.getActivity(), Arrays.asList(dailyBooking));

        listView.setAdapter(dailyBookingAdapter);

        return rootView;    }
}
