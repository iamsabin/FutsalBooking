package android.com.futsalbooking.adapter;

import android.app.Activity;
import android.com.futsalbooking.objects.DailyBooking;
import android.com.futsalbooking.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sabin-HP on 6/1/2016.
 */
public class DailyBookingAdapter extends ArrayAdapter<DailyBooking> {
    private static final String LOG_TAG = DailyBookingAdapter.class.getSimpleName();

    public DailyBookingAdapter(Activity context, List<DailyBooking> dailyBookings) {
        super(context, 0, dailyBookings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DailyBooking dailyBooking = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_daily_booking, parent, false);
        }
        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_book_imageview);

        if(dailyBooking.condition) {
            iconView.setImageResource(R.drawable.book_open);
        } else {
            iconView.setImageResource(R.drawable.book_close);
        }

        TextView timeTextView = (TextView) convertView.findViewById(R.id.list_item_book_time_textview);
        timeTextView.setText(dailyBooking.time);

        TextView usernameTextView = (TextView) convertView.findViewById(R.id.list_item_book_user_textview);
        usernameTextView.setText(dailyBooking.username);

        return convertView;
    }
}


