package com.szymon.hackathonapplication.models.challenges;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.models.fruits.FruitType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Challenge implements Parcelable {
    public Set<FruitType> fruitTypes= new HashSet<>();
    public String title;
    public String description;
    public int timeInMinutes;
    public int howManyToCollect;
    public Integer pointsReward;
    public Integer  iconId;

    public Drawable getFruitIcon() {
        return AppResources.getDrawable(iconId);
    }

    public Challenge() {

    }

    public Challenge(final Parcel in) {
        timeInMinutes = in.readInt();
        pointsReward = in.readInt();
        description = in.readString();
        title = in.readString();
        howManyToCollect = in.readInt();
        iconId = in.readInt();

    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(timeInMinutes);
        dest.writeInt(pointsReward);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeInt(howManyToCollect);
        dest.writeInt(iconId);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
