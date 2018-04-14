package com.szymon.hackathonapplication.models.challenges;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

import java.util.ArrayList;

public class AppleTimeChallenge extends Challenge {

    public AppleTimeChallenge() {
        timeInMinutes = 5;
        pointsReward = 20;
        description = "lorem ipsum";
        title = "lorem ipsum";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_apple);
    }
}
