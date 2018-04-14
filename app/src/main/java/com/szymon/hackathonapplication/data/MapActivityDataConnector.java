package com.szymon.hackathonapplication.data;

import com.szymon.hackathonapplication.interfaces.MapMVP;

public class MapActivityDataConnector implements MapMVP.Model {

    private MapMVP.Presenter presenter;

    public MapActivityDataConnector(final MapMVP.Presenter presenter) {
        this.presenter = presenter;
    }
}
