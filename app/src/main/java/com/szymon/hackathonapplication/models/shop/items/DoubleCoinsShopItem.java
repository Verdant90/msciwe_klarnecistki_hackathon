package com.szymon.hackathonapplication.models.shop.items;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.models.shop.ShopItem;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleCoinsShopItem extends ShopItem {

    private CountDownTimer timer;

    public DoubleCoinsShopItem() {
        super("DoubleCoins", "Receive double coins in next 5 minutes.", 10L);

        this.timer = new CountDownTimer(1000 * 5, 1000) {

            @Override
            public void onTick(long l) {
                // do nothing
            }

            @Override
            public void onFinish() {
                // TODO TCI implementation
                Toast.makeText(getContext(), "DoubleCoins finished!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "DoubleCoins!", Toast.LENGTH_SHORT).show();
        timer.start();
    }
}
