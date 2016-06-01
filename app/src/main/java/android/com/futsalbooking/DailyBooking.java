package android.com.futsalbooking;

/**
 * Created by Sabin-HP on 6/1/2016.
 */
public class DailyBooking {
    public String time;
    public String username;
    public int imageID;

    public DailyBooking(int imageID, String time, String username) {
        this.imageID = imageID;
        this.time = time;
        this.username = username;
    }
}
