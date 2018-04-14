package com.szymon.hackathonapplication.models;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.models.fruits.Apple;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.models.fruits.Pear;
import com.szymon.hackathonapplication.models.fruits.Plum;

import java.util.ArrayList;
import java.util.List;

import static com.szymon.hackathonapplication.helpers.MapUtils.distanceBetween;

public class FruitFactory {

    private static final int APPROX_NUMBER_OF_APPLES_PER_10_KM  = 20;
    private static final int APPROX_NUMBER_OF_PEARS_PER_10_KM  = 13;
    private static final int APPROX_NUMBER_OF_PLUMS_PER_10_KM  = 7;

    private LocationFactory locationFactory;

    public FruitFactory() {
        this.locationFactory = new LocationFactory();
    }

    public List<Fruit> getFruits(final LatLng northWestCorner, final LatLng southEastCorner) {
        final int numberOfApples = calculateNumberOfFruits(Apple.class.getSimpleName(), northWestCorner, southEastCorner);
        final int numberOfPears = calculateNumberOfFruits(Pear.class.getSimpleName(), northWestCorner, southEastCorner);
        final int numberOfPlums = calculateNumberOfFruits(Plum.class.getSimpleName(), northWestCorner, southEastCorner);
        final List<Fruit> result = new ArrayList<>();

        for (int i = 0; i < numberOfApples; ++i ) {
            result.add(new Apple(locationFactory.getRandomLocation(northWestCorner, southEastCorner)));
        }

        for (int i = 0; i < numberOfPears; ++i ) {
            result.add(new Pear(locationFactory.getRandomLocation(northWestCorner, southEastCorner)));
        }

        for (int i = 0; i < numberOfPlums; ++i ) {
            result.add(new Plum(locationFactory.getRandomLocation(northWestCorner, southEastCorner)));
        }
        return result;
    }


    private int calculateNumberOfFruits(final String fruitName, final LatLng northWestCorner, final LatLng southEastCorner) {
        int fruitDensity = APPROX_NUMBER_OF_APPLES_PER_10_KM;
        switch (fruitName) {
            case "Pear":
                fruitDensity = APPROX_NUMBER_OF_PEARS_PER_10_KM;
                break;
            case "Plum":
                fruitDensity = APPROX_NUMBER_OF_PLUMS_PER_10_KM;
                break;
            case "Apple":
                fruitDensity = APPROX_NUMBER_OF_APPLES_PER_10_KM;
                break;
        }
        final LatLng northEastCorner = new LatLng(northWestCorner.latitude, southEastCorner.longitude);
        final LatLng southWestCorner = new LatLng(southEastCorner.latitude, northWestCorner.longitude);

        final float distanceLat = distanceBetween(northEastCorner, northWestCorner);
        final float distanceLng = distanceBetween(southEastCorner, southWestCorner);

        final float numberOfFruitsLat = (distanceLat / 10000) * fruitDensity;
        final float numberOfFruitsLng = (distanceLng / 10000) * fruitDensity;

        return (int) (numberOfFruitsLat * numberOfFruitsLng + 1);
    }

}
