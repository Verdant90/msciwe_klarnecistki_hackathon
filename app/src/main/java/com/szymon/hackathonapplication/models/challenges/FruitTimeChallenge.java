package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.models.fruits.FruitType;

import java.util.Arrays;
import java.util.Collections;

public class FruitTimeChallenge extends Challenge {
    @Override
    public void applyRewardEffect() {
        AppPreferences.increaseFruitChallengeCount();
        AppPreferences.addYabCoins(pointsReward);
    }

    public FruitTimeChallenge() {

        super(Arrays.asList(FruitType.APPLE,FruitType.PEAR, FruitType.PLUM));
        timeInMinutes = 5;
        pointsReward = 100;
        description = "Collect 20 fruits within 5 minutes.";
        title = "GASTROPHASE";
        howManyToCollect = 20;
        iconId = R.drawable.ic_fruit_challenge;
    }

    protected FruitTimeChallenge(final Parcel in) {
        super(in);
    }


    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(final Parcel in) {
            return new FruitTimeChallenge(in);
        }

        @Override
        public Challenge[] newArray(final int size) {
            return new Challenge[size];
        }
    };
}
