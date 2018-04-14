package com.szymon.hackathonapplication.activities;


import android.app.Activity;
import android.os.Bundle;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

import butterknife.ButterKnife;

public class StatisticsActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        AppResources.getInstance();
    }
}
