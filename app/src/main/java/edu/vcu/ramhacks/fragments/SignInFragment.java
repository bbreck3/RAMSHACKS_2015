package edu.vcu.ramhacks.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.RamApplication;
import edu.vcu.ramhacks.activities.BaseActivity;
import edu.vcu.ramhacks.activities.StatusActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class SignInFragment extends Fragment {

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
