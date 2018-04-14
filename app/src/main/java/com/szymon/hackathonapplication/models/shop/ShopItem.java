package com.szymon.hackathonapplication.models.shop;

import android.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ShopItem implements View.OnClickListener {

    public interface Callback {
        void onShopItemPurchased();
    }

    private final String title;
    private final String description;
    private final Long cost;

    protected final Callback callback;

}
