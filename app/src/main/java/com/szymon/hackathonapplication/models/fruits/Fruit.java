package com.szymon.hackathonapplication.models.fruits;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import lombok.Getter;

public abstract class Fruit {
    @Getter
    public LatLng location;
    public int iconResId;
    public Long pointsReward;
    public int experience;
    public boolean claimed;
    public abstract void applyRewardEffect();

    public Fruit(final LatLng location) {
        this.location = location;
    }

    //id to drawable for google map purposes
    public static BitmapDescriptor getMarkerIconFromDrawable(final Drawable drawable) {
        final Canvas canvas = new Canvas();
        final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
