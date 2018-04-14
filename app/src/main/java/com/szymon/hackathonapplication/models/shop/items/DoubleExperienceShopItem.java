package com.szymon.hackathonapplication.models.shop.items;

import android.view.View;
import android.widget.Toast;

import com.szymon.hackathonapplication.models.shop.ShopItem;

import static com.szymon.hackathonapplication.HackatonApplication.getContext;

public class DoubleExperienceShopItem extends ShopItem {

    public DoubleExperienceShopItem() {
        super("DoubleExperience", "Receive double experience in next 5 minutes.", 20L);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "DoubleExperience!", Toast.LENGTH_SHORT).show();
    }
}
