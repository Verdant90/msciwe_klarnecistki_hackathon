package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.models.fruits.FruitType;

import java.util.Arrays;

public class PearTimeChallenge extends Challenge {

    @Override
    public void applyRewardEffect() {
        AppPreferences.increasePearChallengeCount();
        AppPreferences.addYabCoins(pointsReward);
    }

    public PearTimeChallenge() {
        super(Arrays.asList(FruitType.PEAR));
        timeInMinutes = 5;
        pointsReward = 40;
        description = "Collect 10 pears within 5 minutes.";
        title = "PEAR-TO-PEAR";
        howManyToCollect = 10;
        iconId = R.drawable.ic_pear_challenge;
    }

    protected PearTimeChallenge(final Parcel in) {
        super(in);
    }

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(final Parcel in) {
            return new PearTimeChallenge(in);
        }

        @Override
        public Challenge[] newArray(final int size) {
            return new Challenge[size];
        }
    };
}
