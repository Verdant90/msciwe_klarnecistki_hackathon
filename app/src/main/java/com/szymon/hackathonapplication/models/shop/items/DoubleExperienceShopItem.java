package com.szymon.hackathonapplication.models.shop.items;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;
import com.szymon.hackathonapplication.models.shop.ShopItemPriceMapper;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleExperienceShopItem extends ShopItem {

    private static final int MINUTE = 1000 * 60;
    private static final int FIVE_MINUTES = MINUTE * 5;
    private static final float BONUS = 2.0f;

    private CountDownTimer timer;

    public DoubleExperienceShopItem(final Callback callback) {
        super("DoubleExperience",
                "Receive double the experience \nin the next 5 minutes.",
                ShopItemPriceMapper.toPrice(DoubleExperienceShopItem.class), R.drawable.ic_double_exp,
                callback);
        this.timer = new CountDownTimer(FIVE_MINUTES, MINUTE) {

            @Override
            public void onTick(long l) {} // do nothing

            @Override
            public void onFinish() {
                AppPreferences.resetExperienceBonusMultiplier();
                // TODO Toast
                Toast.makeText(getContext(), getTitle() + " bonus finished!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean isAvailable() {
        return AppPreferences.getYabCoins() >= getCost() &&
                !AppPreferences.isExperienceBonusMultiplierActive();
    }

    @Override
    public void onClick(final View view) {
        AppPreferences.setExperienceBonusMultiplier(BONUS);
        timer.start();

        // TODO Toast
        Toast.makeText(getContext(), getTitle() + " bonus is active!", Toast.LENGTH_SHORT).show();

        this.callback.onShopItemPurchased();
    }
}
