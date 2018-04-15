package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.fruits.FruitType;

public class PlumTimeChallenge extends Challenge {
    @Override
    public void applyRewardEffect() {
        AppPreferences.increasePlumChallengeCount();
        AppPreferences.addYabCoins(pointsReward);
    }

    public PlumTimeChallenge() {
        fruitTypes.add(FruitType.PLUM);
        timeInMinutes = 5;
        pointsReward = 50;
        description = "Collect 10 plums within 5 minutes.";
        title = "PLUMmer Time";
        howManyToCollect = 10;
        iconId = R.drawable.ic_plum_challenge;
    }

    protected PlumTimeChallenge(final Parcel in) {
        super(in);
    }

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(final Parcel in) {
            return new PlumTimeChallenge(in);
        }

        @Override
        public Challenge[] newArray(final int size) {
            return new Challenge[size];
        }
    };
}
