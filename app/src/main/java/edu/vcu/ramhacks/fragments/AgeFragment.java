package edu.vcu.ramhacks.fragments;

import android.app.Activity;
import android.app.Application;
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
import edu.vcu.ramhacks.RamApplication;
import edu.vcu.ramhacks.interfaces.BankCallback;
import edu.vcu.ramhacks.utils.BankCallbackStatus;
import edu.vcu.ramhacks.utils.BankUtil;
import edu.vcu.ramhacks.utils.ZipPopulationUtil;

public class AgeFragment extends Fragment {
    @InjectView(R.id.first_name_box)
    EditText firstNameBox;
    @InjectView(R.id.last_name_box)
    EditText lastNameBox;
    @InjectView(R.id.submit_button)
    Button submitButton;
    @InjectView(R.id.age_result)
    TextView ageResultView;

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
        String firstName = firstNameBox.getText().toString();
        String lastName = lastNameBox.getText().toString();
        firstNameBox.setError(null);
        lastNameBox.setError(null);
        boolean error = false;

        if(lastName.isEmpty()) {
            error = true;
            lastNameBox.setError("Field cannot be empty");
            lastNameBox.requestFocus();
        }
        if(firstName.isEmpty()) {
            error = true;
            firstNameBox.setError("Field cannot be empty");
            firstNameBox.requestFocus();
        }
        if(!error){
            submitButton.setEnabled(false);
            final BankUtil bank = new BankUtil();
            bank.getProbability(firstName, lastName, new BankCallback() {
                @Override
                public void onResult(final BankUtil.ProbWeight result, BankCallbackStatus status) {
                    submitButton.setEnabled(true);
                    final ZipPopulationUtil zip = ((RamApplication)(getActivity().getApplication())).getZipPopulationUtil();
                    switch (status) {
                        case OK:
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ageResultView.clearComposingText();
                                    ageResultView.setText("Probability: " + result.getProbability()/result.getWeight()
                                            + "\nZip code: " + (bank.getZipCode() < 0? "not found" : "" + bank.getZipCode())
                                            + "\nPopulation: " + (bank.getZipCode() < 0? "not found" : "" + zip.getPopulation(bank.getZipCode())));
                                }
                            });
                            break;
                        case USER_NOT_FOUND:
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ageResultView.clearComposingText();
                                    ageResultView.setText("User not found");
                                }
                            });
                            break;
                        case UNKNOWN_ERROR:
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ageResultView.clearComposingText();
                                    ageResultView.setText("Unknown error. Check internet connection.");
                                }
                            });
                            break;
                    }
                }
            });
        }

    }
}
