package com.szymon.hackathonapplication.models.fruits;


import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;

public class Pear extends Fruit {

    public Pear(final LatLng location) {
        super(location);
        this.iconResId = R.drawable.ic_pear;
        this.pointsReward = 2L;
        this.experience = 2;
    }

    @Override
    public void applyRewardEffect() {

    }
}
