package android.com.futsalbooking;

import android.com.futsalbooking.adapter.FixturesAdapter;
import android.com.futsalbooking.objects.Fixtures;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class FixturesActivityFragment extends Fragment {
    ListView listView;

    public FixturesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fixtures, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview_fixtures);

        FetchFixturesTask fetchFixturesTask = new FetchFixturesTask();
        fetchFixturesTask.execute("424");

        return rootView;
    }

    public class FetchFixturesTask extends AsyncTask<String, Void, List<Fixtures>> {
        private final String LOG_TAG = FetchFixturesTask.class.getSimpleName();

        private List<Fixtures> getFixturesDataFromJson(String fixturesJsonStr) throws
                JSONException {
            final String FBD_FIXTURES = "fixtures";
            final String FBD_LINKS = "_links";
            final String FBD_HOME = "homeTeam";
            final String FBD_AWAY = "awayTeam";
            final String FBD_HREF = "href";
            final String FBD_DATE = "date";
            final String FBD_HOME_TEAM_NAME = "homeTeamName";
            final String FBD_AWAY_TEAM_NAME = "awayTeamName";
            final String FBD_RESULT = "result";
            final String FBD_HOME_TEAM_GOAL = "goalsHomeTeam";
            final String FBD_AWAY_TEAM_GOAL = "goalsAwayTeam";
            final int FBD_COUNT = 9;

            List<Fixtures> fixtures = new ArrayList<>();

            try {
                JSONObject fixtureJson = new JSONObject(fixturesJsonStr);
                JSONArray fixturesArray = fixtureJson.getJSONArray(FBD_FIXTURES);

                for (int i = fixturesArray.length()-1; i > fixturesArray.length()-FBD_COUNT; i--) {
                    String hrefHome;
                    String hrefAway;
                    String date;
                    String homeTeamName;
                    String awayTeamName;
                    String goalsHomeTeam;
                    String goalsAwayTeam;

                    JSONObject fixturesJson = fixturesArray.getJSONObject(i);
                    JSONObject linksJson = fixturesJson.getJSONObject(FBD_LINKS);
                    JSONObject home = linksJson.getJSONObject(FBD_HOME);
                    hrefHome = home.getString(FBD_HREF);

                    JSONObject away = linksJson.getJSONObject(FBD_AWAY);
                    hrefAway = away.getString(FBD_HREF);

                    date = fixturesJson.getString(FBD_DATE);
                    homeTeamName = fixturesJson.getString(FBD_HOME_TEAM_NAME);
                    awayTeamName = fixturesJson.getString(FBD_AWAY_TEAM_NAME);

                    JSONObject resultJson = fixturesJson.getJSONObject(FBD_RESULT);
                    goalsHomeTeam = resultJson.getString(FBD_HOME_TEAM_GOAL);
                    goalsAwayTeam = resultJson.getString(FBD_AWAY_TEAM_GOAL);

                    fixtures.add(new Fixtures(date, hrefHome, homeTeamName, goalsHomeTeam,
                            hrefAway, awayTeamName, goalsAwayTeam));

                    Log.v(LOG_TAG, "date: " + date +
                            "\nhomeTeamName: " + homeTeamName +
                            "\ngoalsHomeTeam: " + goalsHomeTeam +
                            "\nawayTeamName: " + awayTeamName +
                            "\ngoalsAwayTeam: " + goalsAwayTeam);

                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return fixtures;
        }

        @Override
        protected List<Fixtures> doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String fixturesJsonStr = null;

//            Date date = new Date(TabsBookingAdapter.unixTimeStamp);
//            SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
//            dayFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//            String ddate = dayFormat.format(date);

            try {
                final String FOOTBALL_BASE_URL =
                        "http://api.football-data.org/v1/soccerseasons/" + params[0] + "/fixtures";

                Uri builtUri = Uri.parse(FOOTBALL_BASE_URL).buildUpon()
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                fixturesJsonStr = buffer.toString();
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

            try {
                return getFixturesDataFromJson(fixturesJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(List<Fixtures> fixtures) {
            FixturesAdapter fixturesAdapter = new FixturesAdapter(getActivity(), fixtures);
            listView.setAdapter(fixturesAdapter);
        }
    }
}