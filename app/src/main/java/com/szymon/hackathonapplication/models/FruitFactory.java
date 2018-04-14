package com.szymon.hackathonapplication.models;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.models.fruits.Apple;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.models.fruits.Pear;
import com.szymon.hackathonapplication.models.fruits.Plum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.szymon.hackathonapplication.helpers.map.MapUtils.distanceBetween;

public class FruitFactory {

    private static final FruitFactory fruitFactory = new FruitFactory();

    private static final Random random = new Random(System.currentTimeMillis());

    private static final int APPROX_NUMBER_OF_APPLES_PER_10_KM  = 20;
    private static final int APPROX_NUMBER_OF_PEARS_PER_10_KM  = 13;
    private static final int APPROX_NUMBER_OF_PLUMS_PER_10_KM  = 7;

    private LocationFactory locationFactory;

    private FruitFactory() { // singleton
        this.locationFactory = new LocationFactory();
    }

    public static FruitFactory getInstance() {
        return fruitFactory;
    }

    public List<Fruit> getFruits(final LatLng northWestCorner, final LatLng southEastCorner, int fruitsCount) {
        final List<Fruit> fruits = new LinkedList<>();
        for (int i = 0; i < fruitsCount; i++) {
            final LatLng randomLocation = locationFactory.getRandomLocation(northWestCorner, southEastCorner);
            switch (random.nextInt(3)) {
                case 0: fruits.add(new Apple(randomLocation)); break;
                case 1: fruits.add(new Pear(randomLocation)); break;
                case 2: fruits.add(new Plum(randomLocation)); break;
                default: throw new IllegalStateException("Random returned incorrect value!");
            }
        }
        return fruits;
    }

    public List<Fruit> getFruits(final LatLng northWestCorner, final LatLng southEastCorner) {
        final int numberOfApples = calculateNumberOfFruits(Apple.class.getSimpleName(), northWestCorner, southEastCorner);
        final int numberOfPears = calculateNumberOfFruits(Pear.class.getSimpleName(), northWestCorner, southEastCorner);
        final int numberOfPlums = calculateNumberOfFruits(Plum.class.getSimpleName(), northWestCorner, southEastCorner);
        final List<Fruit> result = new LinkedList<>();

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
