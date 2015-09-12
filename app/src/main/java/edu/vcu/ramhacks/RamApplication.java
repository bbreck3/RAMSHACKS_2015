package edu.vcu.ramhacks;

import android.app.Application;

import timber.log.Timber;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;



public class RamApplication extends Application{
  private String username;

  @Override
  public void onCreate() {

    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
  public String getUsername(){
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
}
