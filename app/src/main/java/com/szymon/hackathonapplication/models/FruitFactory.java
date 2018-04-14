package com.szymon.hackathonapplication.models;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.models.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitFactory {
    private LocationFactory locationFactory;

    public FruitFactory() {
        this.locationFactory = new LocationFactory();
    }

    public List<Fruit> getFruits(final LatLng northWestCorner, final LatLng southEastCorner) {
        final List<Fruit> result = new ArrayList<>();
        return result;
    }

}
