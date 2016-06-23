package android.com.futsalbooking;

import android.com.futsalbooking.adapter.DailyBookingAdapter;
import android.com.futsalbooking.objects.DailyBooking;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Sabin-HP on 6/8/2016.
 */
public class DailyBookingFragment extends Fragment {
    final static String LOG_TAG = DailyBookingFragment.class.getSimpleName();
    ListView listView;
    DailyBookingAdapter dailyBookingAdapter;
    String playTime;

    public static DailyBookingFragment newInstance(Long unixTimeStamp) {
        DailyBookingFragment myFragment = new DailyBookingFragment();

        Bundle args = new Bundle(1);

        args.putLong("timeValue", unixTimeStamp);

        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_daily_booking, container, false);
        Long unixTimeStamp = getArguments().getLong("timeValue");

        Date d = new Date(unixTimeStamp);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
        dayFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = dayFormat.format(d);

        TextView dailyDate = (TextView) rootView.findViewById(R.id.textview_daily_booking);
        dailyDate.setText(date);

        FetchDailyBook fetchDailyBook = new FetchDailyBook();
        fetchDailyBook.execute(date);



        listView = (ListView) rootView.findViewById(R.id.listview_daily_booking);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DailyBooking dailyBooking = dailyBookingAdapter.getItem(position);

                if(dailyBooking.username == null) {
                    Intent intent = new Intent(getActivity(), ConfirmActivity.class);
                    intent.putExtra("time", playTime);
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    public class FetchDailyBook extends AsyncTask<String, Void, List<DailyBooking>> {
        @Override
        protected List<DailyBooking> doInBackground(String... params) {
            String username;

            playTime = "8-9";

            List<DailyBooking> dailyBookings = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                if (i % 2 == 0) {
                    username = "sakazuki";
                } else {
                    username = null;
                }
                DailyBooking dailyBooking = new DailyBooking(playTime, username);
                dailyBookings.add(dailyBooking);
            }

            return dailyBookings;
        }

        @Override
        protected void onPostExecute(List<DailyBooking> dailyBookings) {
            dailyBookingAdapter = new DailyBookingAdapter(getActivity(), dailyBookings);
            listView.setAdapter(dailyBookingAdapter);
        }
    }
}
