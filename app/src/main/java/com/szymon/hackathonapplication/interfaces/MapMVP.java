package com.szymon.hackathonapplication.interfaces;


public interface MapMVP {
    interface View {
    }

    interface Presenter {
        void loadFruits();
    }

    interface Model {
        void loadFruits();
    }
}
