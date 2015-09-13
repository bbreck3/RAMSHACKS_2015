package edu.vcu.ramhacks.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.vcu.ramhacks.R;

public class EEGFragment extends Fragment {

    public static EEGFragment newInstance(String param1, String param2) {
        EEGFragment fragment = new EEGFragment();
        return fragment;
    }

    public EEGFragment() {
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
        return inflater.inflate(R.layout.fragment_eeg, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
