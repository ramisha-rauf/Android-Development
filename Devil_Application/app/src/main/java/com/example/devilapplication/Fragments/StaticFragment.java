package com.example.devilapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.devilapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {
    private static final int YES = 0;
    private static final int NO = 1;

    public StaticFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        final View rootView =
                inflater.inflate(R.layout.fragment_static, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton;
                radioButton = radioGroup.findViewById(checkedId);
                int index;
                index = radioGroup.indexOfChild(radioButton);
                TextView textView;
                textView = rootView.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES: // User chose "Yes."
                        textView.setText(R.string.yes_message);
                        break;
                        case NO: // User chose "No."
                            textView.setText(R.string.no_message);
                            break;
                            default: // No choice made.
                                // Do nothing.
                                break;
                }
            }
        });

        // Return the View for the fragment's UI.
        return rootView;

    }

    public static StaticFragment newInstance() {
        return new StaticFragment();
    }

}