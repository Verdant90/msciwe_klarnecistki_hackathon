package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.models.fruits.FruitType;

public class FruitTimeChallenge extends Challenge {
    public FruitTimeChallenge() {
        fruitTypes.add(FruitType.APPLE);
        fruitTypes.add(FruitType.PEAR);
        fruitTypes.add(FruitType.PLUM);
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
