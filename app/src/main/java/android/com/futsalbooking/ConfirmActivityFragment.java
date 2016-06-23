package android.com.futsalbooking;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfirmActivityFragment extends Fragment {
    private final String LOG_TAG = ConfirmActivityFragment.class.getSimpleName();
    Button confirmButton;
    public ConfirmActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_confirm, container, false);

        TextView availableTimeTextView = (TextView) rootView.findViewById(R.id.time_available_text_view);
        final EditText usernameEditText = (EditText) rootView.findViewById(R.id.user_name_edit_text);
        confirmButton = (Button) rootView.findViewById(R.id.confirm_book_button);

        Intent intent = getActivity().getIntent();
        availableTimeTextView.setText(intent.getStringExtra("time"));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.getText().toString();
                usernameEditText.setText("");
            }
        });
        return rootView;
    }

}
