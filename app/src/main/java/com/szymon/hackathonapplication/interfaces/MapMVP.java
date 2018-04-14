package com.szymon.hackathonapplication.interfaces;


import com.szymon.hackathonapplication.models.fruits.Fruit;

import java.util.List;

public interface MapMVP {
    interface View {
        void showGeneratedFruits(final List<Fruit> fruits);
    }

    interface Presenter {
        void loadFruits();

        void fruitsLoaded(final List<Fruit> fruits);
    }

    interface Model {
        void loadFruits();
    }
}
