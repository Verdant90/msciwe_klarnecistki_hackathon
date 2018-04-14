package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.models.fruits.FruitType;


public class AppleTimeChallenge extends Challenge {

    public AppleTimeChallenge() {
        fruitTypes.add(FruitType.APPLE);
        timeInMinutes = 5;
        pointsReward = 20;
        description = "Collect 10 apples within 5 minutes.";
        title = "iCHALLENGE";
        howManyToCollect = 10;
        iconId = R.drawable.ic_apple_challenge;
    }

    protected AppleTimeChallenge(final Parcel in) {
        super(in);
    }


    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(final Parcel in) {
            return new AppleTimeChallenge(in);
        }

        @Override
        public Challenge[] newArray(final int size) {
            return new Challenge[size];
        }
    };

}
