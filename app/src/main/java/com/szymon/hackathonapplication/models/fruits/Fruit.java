package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.AppResources;

import lombok.Getter;

public abstract class Fruit {

    public FruitType fruitType;
    @Getter
    public LatLng location;
    @Getter
    int iconResId;
    public long yabCoinsReward;
    public int experienceReward;
    private Marker marker;
    public boolean claimed;

    //TODO implement effects
    public abstract void applyRewardEffect();

    public Fruit(final LatLng location) {
        this.location = location;
    }

    public BitmapDescriptor getFruitIcon() {
        return AppResources.getBitmapDescriptor(iconResId);
    }

    public void drawOnMap(final GoogleMap googleMap) {
        this.marker = googleMap.addMarker(new MarkerOptions()
                .position(getLocation())
                .icon(AppResources.getBitmapDescriptor(getIconResId())));
    }

    public void eatFruit() {
        if (marker != null) {
            marker.remove();
        }
        AppPreferences.addYabCoins(yabCoinsReward);
        AppPreferences.addExperiencePoints(experienceReward);
        applyRewardEffect();
    }

    public FruitType getType(){
        return fruitType;
    }
}
