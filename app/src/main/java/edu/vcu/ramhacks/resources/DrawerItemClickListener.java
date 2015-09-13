package edu.vcu.ramhacks.resources;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.InjectView;
import edu.vcu.ramhacks.R;
import edu.vcu.ramhacks.fragments.EEGFragment;
import edu.vcu.ramhacks.fragments.HeartRateFragment;
import edu.vcu.ramhacks.fragments.StressFragment;

/**
 * Created by rob on 9/12/15.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerList;



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }
=
    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HeartRateFragment();
                break;
            case 1:
                fragment = new EEGFragment();
                break;
            case 2:
                fragment = new StressFragment();
                break;
            case 3:
                fragment = new StressFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


}
