package com.szymon.hackathonapplication.models.challenges;

import android.os.Parcel;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class PearTimeChallenge extends Challenge {

    public PearTimeChallenge() {

        timeInMinutes = 5;
        pointsReward = 40;
        description = "Collect 10 pears within 5 minutes.";
        title = "PEAR-TO-PEAR";
        howManyToCollect = 10;
        icon = AppResources.getDrawable(R.drawable.ic_pear_challenge);
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
