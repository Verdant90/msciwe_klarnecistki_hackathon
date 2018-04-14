package com.szymon.hackathonapplication.models.shop;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.szymon.hackathonapplication.helpers.AppResources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class ShopItem implements View.OnClickListener {

    private final String title;
    private final String description;
    private final Long cost;
    private final int iconId;

    public Drawable getShopItemIcon() {
        return AppResources.getDrawable(iconId);
    }

    public interface Callback {
        void onShopItemPurchased();
    }

    protected final Callback callback;

    public abstract boolean isAvailable();

}
