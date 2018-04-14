package com.szymon.hackathonapplication.models.challenges;

import android.graphics.drawable.Drawable;

public abstract class Challenge {
    public String title;
    public String description;
    public int timeInMinutes;
    public int howManyToCollect;
    public Integer pointsReward;
    public Drawable icon;
}
