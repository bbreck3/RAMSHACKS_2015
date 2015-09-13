package edu.vcu.ramhacks.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vcu.ramhacks.R;

public class StressFragment extends Fragment {

    public static StressFragment newInstance(String param1, String param2) {
        StressFragment fragment = new StressFragment();
        return fragment;
    }

    public StressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stress, container, false);
    }

}
