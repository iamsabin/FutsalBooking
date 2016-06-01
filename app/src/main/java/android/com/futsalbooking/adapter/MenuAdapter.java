package android.com.futsalbooking.adapter;

import android.app.Activity;
import android.com.futsalbooking.Menu;
import android.com.futsalbooking.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sabin-HP on 5/29/2016.
 */
public class MenuAdapter extends ArrayAdapter<Menu> {
    private static final String LOG_TAG = MenuAdapter.class.getSimpleName();

    public MenuAdapter(Activity context, List<Menu> menus) {

        super(context, 0, menus);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Menu menu = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item_menu, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.grid_item_icon);
        iconView.setImageResource(menu.image);

        TextView menuTextview = (TextView) convertView.findViewById(R.id.grid_item_menu_name);
        menuTextview.setText(menu.menuName);

        return convertView;
    }
}

