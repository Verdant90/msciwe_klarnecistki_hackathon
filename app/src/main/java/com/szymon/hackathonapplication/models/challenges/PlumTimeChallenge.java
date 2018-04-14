package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class PlumTimeChallenge extends Challenge {
    public PlumTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 50;
        description = "Collect 10 plums within 5 minutes.";
        title = "PLUMmer Time";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_plum_challenge);
    }
}
