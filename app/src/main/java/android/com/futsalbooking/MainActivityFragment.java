package android.com.futsalbooking;

import android.com.futsalbooking.adapter.MenuAdapter;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Menu menu = menuAdapter.getItem(position);


                String menuName = menu.menuName;
                try {
                    Intent intent = new Intent(getActivity(), Class.forName(
                            "android.com.futsalbooking."
                            + menuName
                    ));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Log.e(LOG_TAG, menu.menuName + " class not found exception");
                }

            }
        });

        return rootView;
    }
}
