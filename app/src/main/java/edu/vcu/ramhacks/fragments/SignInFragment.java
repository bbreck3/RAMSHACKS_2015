package edu.vcu.ramhacks.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.RamApplication;
import edu.vcu.ramhacks.activities.BaseActivity;
import edu.vcu.ramhacks.activities.StatusActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class SignInFragment extends Fragment {

    private static final String TAG = "Signinfragment";
    @InjectView(R.id.edit_sign_in_user)
    EditText editUser;
    @InjectView(R.id.edit_sign_in_password)
    EditText editPassword;
    @InjectView(R.id.button_sign_in)
    Button buttonSignIn;

    public static SignInFragment newInstance(String param1, String param2) {
        return new SignInFragment();
    }

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.inject(this, view);
        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");

        editPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onClick();
                return true;
                }
                return false;
            }
        });
        return view;
    }



    @OnClick(R.id.button_sign_in)
    public void onClick() {
        editUser.setError(null);
        String username = editUser.getText().toString();
        if(username.isEmpty()){
            editUser.setError(getString(R.string.error_field_required));
            return;
        }

        ((RamApplication)(getActivity().getApplication())).setUsername(username);
        ((BaseActivity)getActivity()).startActivity(StatusActivity.class, null, null);
        getActivity().finish();
    }



}
