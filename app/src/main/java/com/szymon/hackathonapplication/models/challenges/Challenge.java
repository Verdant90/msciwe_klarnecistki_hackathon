package com.szymon.hackathonapplication.models.challenges;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.models.fruits.FruitType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

public abstract class Challenge implements Parcelable {

    public abstract void applyRewardEffect();
    @Getter
    private final List<FruitType> fruitTypes;
    public String title;
    public String description;
    public int timeInMinutes;
    public int howManyToCollect;
    public Integer pointsReward;
    public Integer  iconId;

    public Drawable getFruitIcon() {
        return AppResources.getDrawable(iconId);
    }

    public Challenge(List<FruitType> fruitTypes) {
        this.fruitTypes = fruitTypes;
    }

    public Challenge(final Parcel in) {
        timeInMinutes = in.readInt();
        pointsReward = in.readInt();
        description = in.readString();
        title = in.readString();
        howManyToCollect = in.readInt();
        iconId = in.readInt();
        int fruitSize = in.readInt();
        String[] strings = new String[fruitSize] ;
        in.readStringArray(strings);
        fruitTypes = new ArrayList<>();
        for (int i = 0; i < fruitSize; i++) {
            fruitTypes.add(FruitType.valueOf(strings[i]));
        }

    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(timeInMinutes);
        dest.writeInt(pointsReward);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeInt(howManyToCollect);
        dest.writeInt(iconId);
        dest.writeInt(fruitTypes.size());
        String[] strings = new String[fruitTypes.size()];
        for (int i = 0; i < fruitTypes.size(); i++) {
            strings[i] =fruitTypes.get(i).toString();
        }
        dest.writeStringArray(strings);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
