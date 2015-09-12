package edu.vcu.ramhacks.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.fragments.SignInFragment;
import edu.vcu.ramhacks.utils.Constants;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // the sign in fragment is always first
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new SignInFragment(), Constants.TAG_FRAGMENT);
        fragmentTransaction.commit();
    }


}
