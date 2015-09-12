package edu.vcu.ramhacks.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.RamApplication;

public class StatusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Implement user auth
        if(((RamApplication)getApplication()).getUsername()==null){
            startActivity(LoginActivity.class, null, null);
        }
    }
}
