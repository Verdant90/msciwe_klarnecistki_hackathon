package com.szymon.hackathonapplication.presenters;


import com.szymon.hackathonapplication.MapActivityDataConnector;
import com.szymon.hackathonapplication.interfaces.MapMVP;

public class MapActivityPresenter implements MapMVP.Presenter {

    private MapMVP.View view;
    private MapMVP.Model model;

    public MapActivityPresenter(final MapMVP.View view) {
        this.view = view;
        this.model = new MapActivityDataConnector(this);
    }
}
