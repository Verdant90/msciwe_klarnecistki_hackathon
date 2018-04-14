package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class PlumTimeChallenge extends Challenge {
    public PlumTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 50;
        description = "lorem ipsum";
        title = "lorem ipsum";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_plum);
    }
}
