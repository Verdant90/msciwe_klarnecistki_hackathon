package com.szymon.hackathonapplication.models.shop.items;

import com.szymon.hackathonapplication.R;

import java.util.HashMap;
import java.util.Map;

public class BasketVersionIconMapper {

    private static final Map<Integer, Integer> basketVersionIconMap = new HashMap<>();

    static {
        basketVersionIconMap.put(0, R.drawable.basket_1);
        basketVersionIconMap.put(1, R.drawable.basket_2);
        basketVersionIconMap.put(2, R.drawable.basket_3);
        basketVersionIconMap.put(3, R.drawable.basket_4);
        basketVersionIconMap.put(4, R.drawable.basket_5);
    }

    public static Integer toDrawableIcon(int basketVersion) {
        if (!basketVersionIconMap.containsKey(basketVersion)) {
            throw new IllegalStateException("Incorrect basket version: " + basketVersion);
        }

        return basketVersionIconMap.get(basketVersion);
    }

}
