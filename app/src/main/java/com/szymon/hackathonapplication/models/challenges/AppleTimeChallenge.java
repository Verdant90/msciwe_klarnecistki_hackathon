package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;


public class AppleTimeChallenge extends Challenge {

    public AppleTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 20;
        description = "Collect 10 apples within 5 minutes.";
        title = "iCHALLENGE";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_apple_challenge);
    }
}
