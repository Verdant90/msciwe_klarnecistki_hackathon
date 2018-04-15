package com.szymon.hackathonapplication.models.fruits;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;

public class Apple extends Fruit {

    public Apple(final LatLng location) {
        super(location);
        this.fruitType = FruitType.APPLE;
        this.iconResId = R.drawable.ic_apple;
        this.yabCoinsReward = 1L;
        this.experienceReward = 1;
    }

    @Override
    public void applyRewardEffect() {
        AppPreferences.eatApple();
    }
}
