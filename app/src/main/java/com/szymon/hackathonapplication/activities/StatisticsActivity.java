package com.szymon.hackathonapplication.activities;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.AppResources;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    ShareDialog shareDialog;
    @OnClick(R.id.btn_fb_share)
    public void shareOnFB() {
        shareDialog = new ShareDialog(this);
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://hackathon.ihsgdansk.com/"))
                    .setQuote("Fruitsteps rocks! " + AppPreferences.getTotalYabCoins() + " yabCoins today that I got near the BeHealthy Hackathon!")
                    .build();
            shareDialog.show(linkContent);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        AppResources.getInstance();


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        expTextView.setText(String.valueOf(AppPreferences.getExperiencePoints()));
        coinsTextView.setText(String.valueOf(AppPreferences.getYabCoins()));
        totalCoinsTextView.setText(String.valueOf(AppPreferences.getTotalYabCoins()));
        applesTextView.setText(String.valueOf(AppPreferences.getAppleCount()));
        pearsTextView.setText(String.valueOf(AppPreferences.getPearCount()));
        plumsTextView.setText(String.valueOf(AppPreferences.getPlumCount()));
        appleChallengesTextView.setText(String.valueOf(AppPreferences.getAppleChallengeCount()));
        pearChallengesTextView.setText(String.valueOf(AppPreferences.getPearChallengeCount()));
        plumChallengesTextView.setText(String.valueOf(AppPreferences.getPlumChallengeCount()));
        fruitChallengesTextView.setText(String.valueOf(AppPreferences.getFruitChallengeCount()));
        distanceTextView.setText(String.format("%.3f m", AppPreferences.getTotalDistance()));
        areaTextView.setText(String.format("%.3f km\u00B2", AppPreferences.getAreaDiscovered()));
    }
}
