package com.szymon.hackathonapplication.models.shop.items;

import android.view.View;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.shop.ShopItem;
import com.szymon.hackathonapplication.models.shop.ShopItemPriceMapper;

public class BasketUpgradeShopItem extends ShopItem {

    public BasketUpgradeShopItem(final Callback callback) {
        super("BasketUpgrade",
                "Upgrade basket too nicer version.",
                ShopItemPriceMapper.toPrice(BasketUpgradeShopItem.class),
                callback);
    }

    @Override
    public void onClick(View view) {
        AppPreferences.increaseBasketVersion();
        this.callback.onShopItemPurchased();
    }

}
