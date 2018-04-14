package com.szymon.hackathonapplication.models.shop;

import android.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ShopItem implements View.OnClickListener {

    private final String title;
    private final String description;
    private final Long cost;

    public interface Callback {
        void onShopItemPurchased();
    }

    protected final Callback callback;

    public abstract boolean isAvailable();

}
