package com.szymon.hackathonapplication.models.shop.items;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleCoinsShopItem extends ShopItem {

    private static final int MINUTE = 1000 * 60;
    private static final int FIVE_MINUTES = MINUTE * 5;
    private static final float BONUS = 2.0f;

    private CountDownTimer timer;

    public DoubleCoinsShopItem() {
        super("DoubleCoins", "Receive double coins in next 5 minutes.", 10L);

        this.timer = new CountDownTimer(FIVE_MINUTES, MINUTE) {

            @Override
            public void onTick(long l) {} // do nothing

            @Override
            public void onFinish() {
                AppPreferences.resetYabCoinsBonusMultiplier();
                // TODO Toast
                Toast.makeText(getContext(), "DoubleCoins bonus finished!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        // TODO Toast
        Toast.makeText(getContext(), "DoubleCoins!", Toast.LENGTH_SHORT).show();
        AppPreferences.setYabCoinsBonusMultiplier(BONUS);
        timer.start();
    }
}
