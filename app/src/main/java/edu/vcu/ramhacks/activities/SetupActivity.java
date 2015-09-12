package edu.vcu.ramhacks.activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.adapters.DrawerAdapter;
import edu.vcu.ramhacks.resources.DrawerItem;

public class SetupActivity extends AppCompatActivity {
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        if(true){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.options);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

      DrawerItem[] drawerItem = new DrawerItem[4];

        drawerItem[0] = new DrawerItem(R.drawable.ic_action_HR, "HeartRate");
        drawerItem[1] = new DrawerItem(R.drawable.ic_action_EEG, "EEG");
        drawerItem[2] = new DrawerItem(R.drawable.ic_action_SM, "StressMonitor");
        drawerItem[3] = new DrawerItem(R.drawable.ic_action_welcome, "Welcome");
        DrawerAdapter adapter = new DrawerAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
