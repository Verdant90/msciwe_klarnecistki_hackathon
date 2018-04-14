package com.szymon.hackathonapplication.models.shop.items;

import android.view.View;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;
import com.szymon.hackathonapplication.models.shop.ShopItemPriceMapper;

public class BasketUpgradeShopItem extends ShopItem {

    private static final int MAX_BASKET_VERSION = 5;

    public BasketUpgradeShopItem(final Callback callback) {
        super("Basket Case",
                "Upgrade your basket \ntoo a nicer version :)",
                ShopItemPriceMapper.toPrice(BasketUpgradeShopItem.class), R.drawable.basket_plus,
                callback);
    }

    @Override
    public boolean isAvailable() {
        return AppPreferences.getYabCoins() >= getCost() &&
                AppPreferences.getBasketVersion() < MAX_BASKET_VERSION;
    }

    @Override
    public void onClick(final View view) {
        AppPreferences.increaseBasketVersion();
        this.callback.onShopItemPurchased();
    }

}
