package com.szymon.hackathonapplication.models.shop;

import com.szymon.hackathonapplication.models.shop.items.BasketUpgradeShopItem;
import com.szymon.hackathonapplication.models.shop.items.DoubleCoinsShopItem;
import com.szymon.hackathonapplication.models.shop.items.DoubleExperienceShopItem;
import com.szymon.hackathonapplication.models.shop.items.MoreFruitsNearbyShopItem;

import java.util.HashMap;
import java.util.Map;

public class ShopItemPriceMapper {

    private static final Map<Class, Integer> map = new HashMap<>();

    static {
        map.put(DoubleCoinsShopItem.class, 10);
        map.put(DoubleExperienceShopItem.class, 15);
        map.put(MoreFruitsNearbyShopItem.class, 25);
        map.put(BasketUpgradeShopItem.class, 30);
    }

    public static long toPrice(Class<? extends ShopItem> clazz) {
        return map.containsKey(clazz) ? map.get(clazz) : 1_000_000;
    }

}
