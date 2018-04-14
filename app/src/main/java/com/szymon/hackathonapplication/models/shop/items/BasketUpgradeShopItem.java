package com.szymon.hackathonapplication.models.shop.items;

import android.view.View;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;

public class BasketUpgradeShopItem extends ShopItem {

    public BasketUpgradeShopItem(final Callback callback) {
        super("BasketUpgrade", "Upgrade basket too nicer version.", 50L, callback);
    }

    @Override
    public void onClick(View view) {
        AppPreferences.increaseBasketVersion();
        this.callback.onShopItemPurchased();
    }

}
