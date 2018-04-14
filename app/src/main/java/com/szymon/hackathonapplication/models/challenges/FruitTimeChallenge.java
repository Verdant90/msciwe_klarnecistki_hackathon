package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class FruitTimeChallenge extends Challenge {
    public FruitTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 100;
        description = "lorem ipsum";
        title = "lorem ipsum";
        howManyToCollect = 20;
        icon = AppResources.getDrawable(R.drawable.ic_apple);
    }
}
