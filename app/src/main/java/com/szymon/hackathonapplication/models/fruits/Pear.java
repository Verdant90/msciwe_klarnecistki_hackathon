package com.szymon.hackathonapplication.models.fruits;


import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;

public class Pear extends Fruit {

    public Pear(final LatLng location) {
        super(location);
        this.fruitType = FruitType.PEAR;
        this.iconResId = R.drawable.ic_pear;
        this.yabCoinsReward = 2L;
        this.experienceReward = 2;
    }

    @Override
    public void applyRewardEffect() {
        AppPreferences.eatPear();
    }
}
