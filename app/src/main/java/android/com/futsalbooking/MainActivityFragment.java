package android.com.futsalbooking;

import android.com.futsalbooking.adapter.MenuAdapter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private MenuAdapter menuAdapter;

    Menu[] menus = {
            new Menu("Booking", R.drawable.booking),
            new Menu("Calendar", R.drawable.calendar),
            new Menu("Fixtures", R.drawable.fixtures),
            new Menu("News", R.drawable.news),
            new Menu("Tournament", R.drawable.tournament),
            new Menu("Challenge", R.drawable.challenge)
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        menuAdapter = new MenuAdapter(getActivity(), Arrays.asList(menus));

        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.menus_grid);
        gridView.setAdapter(menuAdapter);

        return rootView;
    }
}
