package com.szymon.hackathonapplication.data;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.models.FruitFactory;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.models.fruits.Pear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapActivityDataConnector implements MapMVP.Model {

    private final FruitFactory fruitFactory;
    private MapMVP.Presenter presenter;

    public MapActivityDataConnector(final MapMVP.Presenter presenter) {
        this.presenter = presenter;
        this.fruitFactory = FruitFactory.getInstance();
    }

    @Override
    public void loadFruits() {

        final LatLng northWestCornerLatLng = new LatLng(54.413736, 18.572683);
        final LatLng southEastCornerLatLng = new LatLng(54.3209641,18.7800167);
        final List<Fruit> fruits = fruitFactory.getFruits(northWestCornerLatLng, southEastCornerLatLng);

        // Fixtures
        for (LatLng location : Fixtures.path1) {
            fruits.add(new Pear(location));
        }

        presenter.fruitsLoaded(fruits);
    }

    private static final class Fixtures {
        private static List<LatLng> path1 = Arrays.asList(
                new LatLng(54.367109, 18.631720),
                new LatLng(54.367353, 18.631334),
                new LatLng(54.367891, 18.631151),
                new LatLng(54.368234, 18.631634),
                new LatLng(54.368541, 18.631033),
                new LatLng(54.369291, 18.630892),
                new LatLng(54.370486, 18.629941),
                new LatLng(54.371299, 18.629614),
                new LatLng(54.371992, 18.628603),
                new LatLng(54.373238, 18.627712),
                new LatLng(54.374346, 18.627296),
                new LatLng(54.375247, 18.627504),
                new LatLng(54.375783, 18.629109),
                new LatLng(54.375679, 18.630684),
                new LatLng(54.374675, 18.631130),
                new LatLng(54.372182, 18.628039),
                new LatLng(54.371715, 18.627236),
                new LatLng(54.372459, 18.628871),
                new LatLng(54.372009, 18.629495),
                new LatLng(54.371559, 18.630268),
                new LatLng(54.368269, 18.632348),
                new LatLng(54.367888, 18.632943),
                new LatLng(54.367265, 18.633359),

                new LatLng(54.370780, 18.629554),
                new LatLng(54.370520, 18.628752),
                new LatLng(54.370157, 18.629138),
                new LatLng(54.370399, 18.630030),
                new LatLng(54.370130, 18.629301),
                new LatLng(54.369977, 18.630177),
                new LatLng(54.369483, 18.630323),
                new LatLng(54.369960, 18.630703),
                new LatLng(54.370419, 18.631112),
                new LatLng(54.370130, 18.631813)
        );
    }
}
