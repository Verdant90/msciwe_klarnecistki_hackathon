package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;

public class Apple extends Fruit {

    public Apple(final LatLng location) {
        super(location);
        this.iconResId = R.drawable.ic_apple;
        this.pointsReward = 1L;
        this.experience = 1;
    }

    @Override
    public void applyRewardEffect() {

    }
}
