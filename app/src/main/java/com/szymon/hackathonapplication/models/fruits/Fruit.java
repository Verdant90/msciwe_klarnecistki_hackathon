package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.LatLng;

import lombok.Getter;

public abstract class Fruit {
    @Getter
    public LatLng location;
    public int iconResId;
    public Long pointsReward;
    public int experience;
    public boolean claimed;
    public abstract void applyRewardEffect();
}
