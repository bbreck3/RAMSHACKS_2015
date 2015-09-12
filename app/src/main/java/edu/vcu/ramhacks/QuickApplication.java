package edu.vcu.ramhacks;

import android.app.Application;

import timber.log.Timber;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;



public class QuickApplication extends Application{
  @Override
  public void onCreate() {

    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
