package com.szymon.hackathonapplication.models.shop.items;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.HackatonApplication;
import com.szymon.hackathonapplication.activities.MapActivity;
import com.szymon.hackathonapplication.models.FruitFactory;
import com.szymon.hackathonapplication.models.shop.ShopItem;

public class MoreFruitsNearbyShopItem extends ShopItem {

    private static final int FRUITS_COUNT = 100;
    private static final double NEARBY_RANGE_IN_DEGREES = 0.005;

    public MoreFruitsNearbyShopItem(final Callback callback) {
        super("More fruits nearby", String.format("Show %d more fruits nearby.", FRUITS_COUNT), 35L, callback);
    }

    @Override
    public void onClick(View view) {
        final LatLng location = MapActivity.currentLocation();

        if (location == null) {
            // TODO Toast
            Toast.makeText(HackatonApplication.getContext(), "Your current location is unknown!", Toast.LENGTH_SHORT).show();
            return;
        }

        final double lat = location.latitude;
        final double lng = location.longitude;

        MapActivity.addFruitsToMap(FruitFactory.getInstance()
                .getFruits(toNorthWest(lat, lng), toSouthEast(lat, lng), FRUITS_COUNT));

        callback.onShopItemPurchased();
    }

    @NonNull
    private LatLng toNorthWest(double lat, double lng) {
        return new LatLng(lat - NEARBY_RANGE_IN_DEGREES, lng - NEARBY_RANGE_IN_DEGREES);
    }

    @NonNull
    private LatLng toSouthEast(double lat, double lng) {
        return new LatLng(lat + NEARBY_RANGE_IN_DEGREES, lng + NEARBY_RANGE_IN_DEGREES);
    }

}
