package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class PearTimeChallenge extends Challenge {

    public PearTimeChallenge() {

        timeInMinutes = 5;
        pointsReward = 40;
        description = "lorem ipsum";
        title = "lorem ipsum";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_pear);
    }
}
