package com.szymon.hackathonapplication.helpers;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.szymon.hackathonapplication.HackatonApplication;

public class AppResources {
    private static AppResources instance = null;
    private static Resources resources;

    private AppResources() {
        resources = HackatonApplication.getContext().getResources();
    }

    public static AppResources getInstance() {
        if (instance == null) {
            instance = new AppResources();
        }
        return instance;
    }

    public static String getString(final int id) {
        return resources.getString(id);
    }

    public static String getString(final int id, final Object... formatArgs) {
        return resources.getString(id, formatArgs);
    }

    public static Drawable getDrawable(final int drawableId) {
        return resources.getDrawable(drawableId);
    }

    public static BitmapDescriptor getBitmapDescriptor(final int resId) {
        final Drawable drawable = AppResources.getDrawable(resId);
        final Canvas canvas = new Canvas();
        final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public static int getColor(final int colorId) {
        return ContextCompat.getColor(HackatonApplication.getContext(), colorId);
    }
}
