package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;

public class Plum extends Fruit {

    public Plum(final LatLng location) {
        super(location);
        this.fruitType = FruitType.PLUM;
        this.iconResId = R.drawable.ic_plum;
        this.yabCoinsReward = 3L;
        this.experienceReward = 3;
    }

    @Override
    public void applyRewardEffect() {
        AppPreferences.eatPlum();
    }
}
