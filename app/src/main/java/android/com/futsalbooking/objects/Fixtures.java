package android.com.futsalbooking.objects;

import android.widget.ImageView;

/**
 * Created by Sabin-HP on 6/23/2016.
 */
public class Fixtures {
    public String date;
    public String homeTeamImageURL;
    public String homeTeamName;
    public String goalsHomeTeam;
    public String awayTeamImageURL;
    public String awayTeamName;
    public String goalsAwayTeam;
    public ImageView homeTeamImageView;
    public ImageView awayTeamImageView;

    public Fixtures(String date, String homeTeamImageURL, String homeTeamName, String goalsHomeTeam,
                    String awayTeamImageURL, String awayTeamName, String goalsAwayTeam) {
        this.awayTeamName = awayTeamName;
        this.date = date;
        this.goalsAwayTeam = goalsAwayTeam;
        this.goalsHomeTeam = goalsHomeTeam;
        this.homeTeamName = homeTeamName;
        this.homeTeamImageURL = homeTeamImageURL;
        this.awayTeamImageURL = awayTeamImageURL;
    }
}
