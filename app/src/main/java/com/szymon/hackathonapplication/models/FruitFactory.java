package com.szymon.hackathonapplication.models;

public class FruitFactory {
    private LocationFactory locationFactory;

    public FruitFactory() {
        this.locationFactory = new LocationFactory();
    }
}
