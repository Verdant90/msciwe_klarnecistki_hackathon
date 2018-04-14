package com.szymon.hackathonapplication.presenters;


import com.szymon.hackathonapplication.data.MapActivityDataConnector;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.models.fruits.Fruit;

import java.util.List;

public class MapActivityPresenter implements MapMVP.Presenter {

    private MapMVP.View view;
    private MapMVP.Model model;

    public MapActivityPresenter(final MapMVP.View view) {
        this.view = view;
        this.model = new MapActivityDataConnector(this);
    }

    @Override
    public void loadFruits() {
        model.loadFruits();
    }

    @Override
    public void fruitsLoaded(final List<Fruit> fruits) {
        view.showGeneratedFruits(fruits);
    }
}
