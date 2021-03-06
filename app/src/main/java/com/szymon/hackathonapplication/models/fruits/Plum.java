package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;

public class Plum extends Fruit {

    public Plum(final LatLng location) {
        super(location);
        this.iconResId = R.drawable.ic_plum;
        this.yabCoinsReward = 3L;
        this.experienceReward = 3;
    }

    @Override
    public void applyRewardEffect() {

    }
}
