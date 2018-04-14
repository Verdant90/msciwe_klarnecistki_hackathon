package com.szymon.hackathonapplication.models.shop.items;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleExperienceShopItem extends ShopItem {

    private static final int MINUTE = 1000 * 60;
    private static final int FIVE_MINUTES = MINUTE * 5;
    private static final float BONUS = 2.0f;

    private CountDownTimer timer;

    public DoubleExperienceShopItem(final Callback callback) {
        super("DoubleExperience", "Receive double experience in next 5 minutes.", 20L, callback);
        this.timer = new CountDownTimer(FIVE_MINUTES, MINUTE) {

            @Override
            public void onTick(long l) {} // do nothing

            @Override
            public void onFinish() {
                AppPreferences.resetExperienceBonusMultiplier();
                // TODO Toast
                Toast.makeText(getContext(), "DoubleExperience bonus finished!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        // TODO Toast
        Toast.makeText(getContext(), "DoubleExperience!", Toast.LENGTH_SHORT).show();
        AppPreferences.setExperienceBonusMultiplier(BONUS);
        timer.start();

        this.callback.onShopItemPurchased();
    }
}
