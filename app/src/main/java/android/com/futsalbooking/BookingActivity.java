package android.com.futsalbooking;

import android.com.futsalbooking.adapter.TabsBookingAdapter;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.TimeZone;


/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class BookingActivity extends ActionBarActivity implements
        ActionBar.TabListener {

    static Long unixTimeStamp = null;
    private final String LOG_TAG = BookingActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabsBookingAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles

    public Long getDate() {
        return unixTimeStamp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FetchTime fetchTime = new FetchTime();

        fetchTime.execute("Asia/Kathmandu");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        viewPager = (ViewPager) findViewById(R.id.pager);


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
                                          }

        );
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

    public class FetchTime extends AsyncTask<String, Void, String[]> {


        public String[] doInBackground(String... params) {
            String day[] = new String[7];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String timeJsonStr = null;

            String format = "json";

            try {
                final String TIME_ZONE_DB_API_KEY = "OQD39NMIAXC0";

                final String TIME_ZONE_BASE_URL =
                        "http://api.timezonedb.com/?";
                final String ZONE_PARAM = "zone";
                final String FORMAT_PARAM = "format";
                final String KEY_PARAM = "key";

                Uri builtUri = Uri.parse(TIME_ZONE_BASE_URL).buildUpon()
                        .appendQueryParameter(ZONE_PARAM, params[0])
                        .appendQueryParameter(FORMAT_PARAM, format)
                        .appendQueryParameter(KEY_PARAM, TIME_ZONE_DB_API_KEY)
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                timeJsonStr = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }


            final String TZDB_TIMESTAMP = "timestamp";
            try {
                JSONObject forecastJson = new JSONObject(timeJsonStr);
                unixTimeStamp = forecastJson.getLong(TZDB_TIMESTAMP);
                unixTimeStamp *= 1000;
            } catch (JSONException e) {
                Log.v(LOG_TAG, "json exception found");
            }


            for (int i = 0; i < 7; i++) {
                Date d = new Date(unixTimeStamp + (86400000 * i));
                SimpleDateFormat dayFormat = new SimpleDateFormat("E");
                dayFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

                day[i] = dayFormat.format(d);

            }
            return day;
        }

        protected void onPostExecute(String[] dayTabs) {
            // Adding Tabs

            // Initialization
            try {
                actionBar = getSupportActionBar();

                mAdapter = new TabsBookingAdapter(getSupportFragmentManager());
                viewPager.setAdapter(mAdapter);
                viewPager.setTag("dailyTabs");
                actionBar.setHomeButtonEnabled(false);
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            } catch (NullPointerException e) {
                Log.e(LOG_TAG, "NullPointerException Found");
            }

            for (String tab_name : dayTabs) {
                actionBar.addTab(
                        actionBar.newTab()
                                .setText(tab_name)
                                .setTabListener(BookingActivity.this)
                );
            }
        }
    }

}

