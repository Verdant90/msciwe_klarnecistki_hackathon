package com.szymon.hackathonapplication.data;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.models.FruitFactory;
import com.szymon.hackathonapplication.models.fruits.Fruit;

import java.util.ArrayList;
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
        presenter.fruitsLoaded(fruits);
    }
}
