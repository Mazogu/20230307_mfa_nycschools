package com.example.a20230307_mfa_nycschools;

import android.app.Application;

import timber.log.Timber;

/**
 * Custom application class to plant Timber tree.
 */
public class NYCSchoolsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
