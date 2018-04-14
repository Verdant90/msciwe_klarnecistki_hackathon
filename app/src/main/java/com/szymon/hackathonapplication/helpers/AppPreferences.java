package com.szymon.hackathonapplication.helpers;


import android.content.SharedPreferences;

import com.szymon.hackathonapplication.HackatonApplication;

public class AppPreferences {

    private static final String EXPLORATION_RANGE = "EXPLORATION_RANGE";
    private static final String TOTAL_YAB_COINS = "TOTAL_YAB_COINS";
    private static final String YAB_COINS = "YAB_COINS";
    private static final String APPLE_COUNT = "APPLE_COUNT";
    private static final String PEAR_COUNT = "PEAR_COUNT";
    private static final String PLUM_COUNT = "PLUM_COUNT";
    private static final String EXPERIENCE_POINTS = "EXPERIENCE_POINTS";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor preferencesEdit;

    private static AppPreferences instance = null;

    public static AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        preferences = HackatonApplication.getSharedPreferences();
        preferencesEdit = preferences.edit();
        return instance;
    }

    // Exploration Range

    private void setExplorationRange(final double explorationRange) {
        preferencesEdit.putFloat(EXPLORATION_RANGE, (float) explorationRange).apply();
    }

    public double getExplorationRange() {
        return preferences.getFloat(EXPLORATION_RANGE, 100);
    }

    // Experience Points

    public void addExperiencePoints(final long experiencePoints) {
        final long current = getExperiencePoints();
        setExperiencePoints(current + experiencePoints);
    }

    private void setExperiencePoints(final long experiencePoints) {
        preferencesEdit.putLong(EXPERIENCE_POINTS, experiencePoints).apply();
    }

    private long getExperiencePoints() {
        return preferences.getLong(EXPERIENCE_POINTS, 0);
    }

    // Yab Coins

    public void addYabCoins(final long coins) {
        final long current = getYabCoins();
        final long currentTotal = getTotalYabCoins();

        preferencesEdit.putLong(YAB_COINS, current + coins).apply();
        preferencesEdit.putLong(TOTAL_YAB_COINS, currentTotal + coins).apply();
    }

    public void spendYabCoins(final long coins) {
        final long current = getYabCoins();
        preferencesEdit.putLong(YAB_COINS, current - coins).apply();
    }

    private void setTotalYabCoins(final long coins) {
        preferencesEdit.putLong(TOTAL_YAB_COINS, coins).apply();
    }

    public long getTotalYabCoins() {
        return preferences.getLong(TOTAL_YAB_COINS, 0);
    }

    private void setYabCoins(final long coins) {
        preferencesEdit.putLong(YAB_COINS, coins).apply();
    }

    public long getYabCoins() {
        return preferences.getLong(YAB_COINS, 0);
    }

    // Apple

    private static void setAppleCount(final long points) {
        preferencesEdit.putLong(APPLE_COUNT, points).apply();
    }

    public static long getAppleCount() {
        return preferences.getLong(APPLE_COUNT, 0);
    }

    public static void eatApple() {
        final long current = getAppleCount();
        setAppleCount(current + 1);
    }

    // Pear

    private static void setPearCount(final long points) {
        preferencesEdit.putLong(PEAR_COUNT, points).apply();
    }

    public static long getPearCount() {
        return preferences.getLong(PEAR_COUNT, 0);
    }

    public static void eatPear() {
        final long current = getPearCount();
        setPearCount(current + 1);
    }

    // Plum

    private static void setPlumCount(final long points) {
        preferencesEdit.putLong(PLUM_COUNT, points).apply();
    }

    public static long getPlumCount() {
        return preferences.getLong(PLUM_COUNT, 0);
    }

    public static void eatPlum() {
        final long current = getPlumCount();
        setPlumCount(current + 1);
    }
}
