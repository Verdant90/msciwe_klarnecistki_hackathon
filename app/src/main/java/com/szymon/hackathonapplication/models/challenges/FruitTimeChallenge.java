package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class FruitTimeChallenge extends Challenge {
    public FruitTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 100;
        description = "Collect 20 fruits within 5 minutes.";
        title = "GASTROPHASE";
        howManyToCollect = 20;
        icon = AppResources.getDrawable(R.drawable.ic_fruit_challenge);
    }
}
