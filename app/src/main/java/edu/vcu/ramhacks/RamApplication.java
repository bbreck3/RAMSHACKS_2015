package edu.vcu.ramhacks;

import android.app.Application;

import java.util.HashMap;

import edu.vcu.ramhacks.utils.BankUtil;
import edu.vcu.ramhacks.utils.ZipPopulationUtil;
import timber.log.Timber;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;



public class RamApplication extends Application{
  private String username;
    final ZipPopulationUtil zipPopulationUtil = new ZipPopulationUtil();



    @Override
    public void onCreate() {

    super.onCreate();
    if (BuildConfig.DEBUG) {
        Timber.plant(new Timber.DebugTree());
    }
    zipPopulationUtil.setResources(getResources());
    new Thread(new Runnable() {
        @Override
        public void run() {
            zipPopulationUtil.load();
        }
    }).start();
    }
    public String getUsername(){
    return username;
    }
    public void setUsername(String username) {
    this.username = username;
  }
    public ZipPopulationUtil getZipPopulationUtil() {
        return this.zipPopulationUtil;
    }
}
