package edu.vcu.ramhacks.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import edu.vcu.ramhacks.R;

public class AgeFragment extends Fragment {
    @InjectView(R.id.first_name_box)
    EditText firstName;
    @InjectView(R.id.last_name_box)
    EditText lastName;
    @InjectView(R.id.submit_button)
    Button submit;
    @InjectView(R.id.age_result)
    TextView ageResult;

    public static AgeFragment newInstance() {
        AgeFragment fragment = new AgeFragment();
        return fragment;
    }

    public AgeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_age, container, false);
        ButterKnife.inject(this,view);
        return view;
    }
    @OnClick(R.id.submit_button)
    public void submitClick(){
        //Do stuff
    }
}
