package com.alpha.vidyaroha.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.vidyaroha.R;
import com.alpha.vidyaroha.activities.TabActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Greetings extends Fragment {

    public Greetings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_greetings, container, false);

        // To show the number of times app executed by the user.
        String message = "Hello user , you have entered into the app for " + TabActivity.requiredObject.getCount() + " time today";
        ((TextView)view.findViewById(R.id.greetings_message)).setText(message);
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        return view;
    }

}
