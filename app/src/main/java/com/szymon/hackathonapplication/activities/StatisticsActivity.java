package com.szymon.hackathonapplication.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends Activity {

    @BindView(R.id.text_exp)
    TextView expTextView;
    @BindView(R.id.text_coins)
    TextView coinsTextView;
    @BindView(R.id.text_coins_total)
    TextView totalCoinsTextView;
    @BindView(R.id.text_apples)
    TextView applesTextView;
    @BindView(R.id.text_pears)
    TextView pearsTextView;
    @BindView(R.id.text_plums)
    TextView plumsTextView;
    @BindView(R.id.text_apple_challenges)
    TextView appleChallengesTextView;
    @BindView(R.id.text_pear_challenges)
    TextView pearChallengesTextView;
    @BindView(R.id.text_plum_challenges)
    TextView plumChallengesTextView;
    @BindView(R.id.text_fruit_challenges)
    TextView fruitChallengesTextView;
    @BindView(R.id.text_total_distance)
    TextView distanceTextView;
    @BindView(R.id.text_total_area)
    TextView areaTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        AppResources.getInstance();
    }
}
