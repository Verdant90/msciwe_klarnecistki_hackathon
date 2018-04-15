package com.szymon.hackathonapplication.models.fruits;


import android.location.Location;

import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.map.MapUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FruitsDao {
    private static FruitsDao instance = null;

    private static final List<Fruit> fruitsOnMap = new LinkedList<>();

    private FruitsDao() {
    }

    public static FruitsDao getInstance() {
        if (instance == null) {
            instance = new FruitsDao();
        }
        return instance;
    }

    public static void addFruits(final List<Fruit> fruits) {
        fruitsOnMap.addAll(fruits);
    }

    public static void removeFruitsInRange(final Location location) {
        final Iterator iterator = fruitsOnMap.iterator();
        while (iterator.hasNext()) {
            final Fruit fruit = (Fruit) iterator.next();
            final float distance = MapUtils.distanceBetween(location, fruit.getLocation());
            if (distance <= AppPreferences.getExplorationRange()) {
                fruit.eatFruit();
                iterator.remove();
            }
        }
    }
}
