package com.szymon.hackathonapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.models.fruits.FruitsDao;

import java.lang.ref.WeakReference;

public class HackatonApplication extends Application {
    public static final String HACKATON_APPLICATION_PREFERENCES = "HACKATON_APPLICATION_PREFERENCES";

    private static WeakReference<Application> sApplication;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(HACKATON_APPLICATION_PREFERENCES, MODE_PRIVATE);
        sApplication = new WeakReference<Application>(this);
        AppResources.getInstance();
        AppPreferences.getInstance();
        FruitsDao.getInstance();
        AppPreferences.addYabCoins(100L); // TODO only for tests

    }

    public static Application getApplication() {
        return sApplication.get();
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
