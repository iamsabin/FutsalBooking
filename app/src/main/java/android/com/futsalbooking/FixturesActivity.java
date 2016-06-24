package android.com.futsalbooking;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class FixturesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
