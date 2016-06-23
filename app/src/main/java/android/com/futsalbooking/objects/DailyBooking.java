package android.com.futsalbooking.objects;

/**
 * Created by Sabin-HP on 6/1/2016.
 */
public class DailyBooking {
    public String time;
    public String username;
    public boolean condition;

    public DailyBooking(boolean condition, String time, String username) {
        this.condition = condition;
        this.time = time;
        this.username = username;
    }
}
