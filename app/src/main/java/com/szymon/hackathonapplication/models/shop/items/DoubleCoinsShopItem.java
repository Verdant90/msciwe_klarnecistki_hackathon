package com.szymon.hackathonapplication.models.shop.items;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;
import com.szymon.hackathonapplication.models.shop.ShopItemPriceMapper;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleCoinsShopItem extends ShopItem {

    private static final int MINUTE = 1000; // TODO now is second for tests
    private static final int FIVE_MINUTES = MINUTE * 5;
    private static final float BONUS = 2.0f;

    private CountDownTimer timer;

    public DoubleCoinsShopItem(final Callback callback) {
        super("DoubleCoins",
                "Receive double coins in next 5 minutes.",
                ShopItemPriceMapper.toPrice(DoubleCoinsShopItem.class), R.drawable.ic_double_yabcoins,
                callback);

        this.timer = new CountDownTimer(FIVE_MINUTES, MINUTE) {

            @Override
            public void onTick(long l) {} // do nothing

            @Override
            public void onFinish() {
                AppPreferences.resetYabCoinsBonusMultiplier();
                // TODO Toast
                Toast.makeText(getContext(), getTitle() + " bonus finished!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean isAvailable() {
        return AppPreferences.getYabCoins() >= getCost() &&
                !AppPreferences.isYabCoinsBonusMultiplierActive();
    }

    @Override
    public void onClick(View view) {
        AppPreferences.setYabCoinsBonusMultiplier(BONUS);
        timer.start();

        // TODO Toast
        Toast.makeText(getContext(), getTitle() + " bonus is active!", Toast.LENGTH_SHORT).show();

        this.callback.onShopItemPurchased();
    }
}
