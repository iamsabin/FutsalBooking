package android.com.futsalbooking.objects;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class Menu {
    public String menuName;
    public int image; // drawable reference id

    public Menu(String mName, int image)
    {
        this.menuName = mName;
        this.image = image;
    }

}