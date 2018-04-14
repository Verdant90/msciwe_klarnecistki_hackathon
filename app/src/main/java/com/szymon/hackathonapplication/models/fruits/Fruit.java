package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.helpers.map.MapUtils;

import lombok.Getter;

public abstract class Fruit {
    @Getter
    public LatLng location;
    int iconResId;
    public Long pointsReward;
    public int experience;
    public boolean claimed;
    public abstract void applyRewardEffect();

    public Fruit(final LatLng location) {
        this.location = location;
    }

    public BitmapDescriptor getFruitIcon() {
        return AppResources.getBitmapDescriptor(iconResId);
    }
}
