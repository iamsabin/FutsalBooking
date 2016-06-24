package android.com.futsalbooking.adapter;

import android.app.Activity;
import android.com.futsalbooking.R;
import android.com.futsalbooking.objects.Fixtures;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sabin-HP on 6/23/2016.
 */
public class FixturesAdapter extends ArrayAdapter<Fixtures> {
    private static final String LOG_TAG = FixturesAdapter.class.getSimpleName();
//    Activity context;

    public FixturesAdapter(Activity context, List<Fixtures> fixtures) {
        super(context, 0, fixtures);
//        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fixtures fixtures = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_fixtures, parent, false);
        }

//        fixtures.homeTeamImageView = (ImageView) convertView.findViewById(R.id.list_item_home_team_imageview);

        TextView homeTeamName = (TextView) convertView.findViewById(R.id.list_item_home_team_name);
        homeTeamName.setText(fixtures.homeTeamName);

        TextView goalsHomeTeam = (TextView) convertView.findViewById(R.id.list_item_home_team_goals);
        goalsHomeTeam.setText(fixtures.awayTeamName);

        TextView goalsAwayTeam = (TextView) convertView.findViewById(R.id.list_item_away_team_goals);
        goalsAwayTeam.setText(fixtures.goalsAwayTeam);

        TextView awayTeamName = (TextView) convertView.findViewById(R.id.list_item_away_team_name);
        awayTeamName.setText(fixtures.awayTeamName);

//        fixtures.awayTeamImageView = (ImageView) convertView.findViewById(R.id.list_item_away_team_imageview);

        return convertView;
    }

//    public class FetchImage extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//
//            return null;
//        }
//    }
}



