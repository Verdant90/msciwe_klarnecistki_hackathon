package com.szymon.hackathonapplication.models.shop.items;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.szymon.hackathonapplication.HackatonApplication;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.activities.MapActivity;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.models.FruitFactory;
import com.szymon.hackathonapplication.models.shop.ShopItem;
import com.szymon.hackathonapplication.models.shop.ShopItemPriceMapper;

public class MoreFruitsNearbyShopItem extends ShopItem {

    private static final int FRUITS_COUNT = 10;
    private static final double NEARBY_RANGE_IN_DEGREES = 0.005;

    public MoreFruitsNearbyShopItem(final Callback callback) {
        super("More fruits nearby",
                String.format("Spawn %d fruits nearby.", FRUITS_COUNT),
                ShopItemPriceMapper.toPrice(MoreFruitsNearbyShopItem.class), R.drawable.ic_more_apples,
                callback);
    }

    @Override
    public boolean isAvailable() {
        return AppPreferences.getYabCoins() >= getCost();
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
