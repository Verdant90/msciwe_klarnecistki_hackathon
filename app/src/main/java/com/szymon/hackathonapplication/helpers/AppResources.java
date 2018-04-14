package com.szymon.hackathonapplication.helpers;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.szymon.hackathonapplication.HackatonApplication;

public class AppResources {
    private static AppResources instance = null;
    private static Resources resources;

    private AppResources() {
        resources = HackatonApplication.getContext().getResources();
    }

    public static void initInstance() {
        if (instance == null) {
            instance = new AppResources();
        }
    }

    public static Drawable getDrawable(final int drawableId) {
        return resources.getDrawable(drawableId);
    }

    public static int getColor(final int colorId) {
        return ContextCompat.getColor(HackatonApplication.getContext(), colorId);
    }
}
