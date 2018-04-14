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
    private static final String APPLE_CHALLENGES = "APPLE_CHALLENGES";
    private static final String PEAR_CHALLENGES = "PEAR_CHALLENGES";
    private static final String PLUM_CHALLENGES = "PLUM_CHALLENGES";
    private static final String FRUIT_CHALLENGES = "FRUIT_CHALLENGES";

    private static final String YAB_COINS_BONUS_MULTIPLIER = "YAB_COINS_BONUS_MULTIPLIER";
    private static final float INITIAL_YAB_COINS_BONUS_MULTIPLIER = 1.0f;

    private static final String EXPERIENCE_POINTS_BONUS_MULTIPLIER = "EXPERIENCE_POINTS_BONUS_MULTIPLIER";
    private static final float INITIAL_EXPERIENCE_POINTS_BONUS_MULTIPLIER = 1.0f;
    public static final String BASKET_VERSION = "BASKET_VERSION";

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

    // Basket version

    public static Integer getBasketVersion() {
        return preferences
                .getInt(BASKET_VERSION, 0);
    }

    public static void increaseBasketVersion() {
        preferencesEdit
                .putInt(BASKET_VERSION, getBasketVersion() + 1)
                .apply();
    }

    // Exploration Range

    private static void setExplorationRange(final double explorationRange) {
        preferencesEdit.putFloat(EXPLORATION_RANGE, (float) explorationRange).apply();
    }

    public static double getExplorationRange() {
        return preferences.getFloat(EXPLORATION_RANGE, 100);
    }

    // Experience Points

    public static void addExperiencePoints(final long experiencePoints) {
        final long current = getExperiencePoints();
        final long experiencePointsWithBonus = (long) (experiencePoints * getExperienceBonusMultiplier());
        setExperiencePoints(current + experiencePointsWithBonus);
    }

    private static void setExperiencePoints(final long experiencePoints) {
        preferencesEdit.putLong(EXPERIENCE_POINTS, experiencePoints).apply();
    }

    private static long getExperiencePoints() {
        return preferences.getLong(EXPERIENCE_POINTS, 0);
    }

    public static void setExperienceBonusMultiplier(final float bonus) {
        preferencesEdit
                .putFloat(EXPERIENCE_POINTS_BONUS_MULTIPLIER, bonus)
                .apply();
    }

    public static void resetExperienceBonusMultiplier() {
        preferencesEdit
                .putFloat(EXPERIENCE_POINTS_BONUS_MULTIPLIER, INITIAL_EXPERIENCE_POINTS_BONUS_MULTIPLIER)
                .apply();
    }

    public static boolean isExperienceBonusMultiplierActive() {
        return getExperienceBonusMultiplier() != INITIAL_EXPERIENCE_POINTS_BONUS_MULTIPLIER;
    }

    public static float getExperienceBonusMultiplier() {
        return preferences
                .getFloat(EXPERIENCE_POINTS_BONUS_MULTIPLIER, INITIAL_EXPERIENCE_POINTS_BONUS_MULTIPLIER);
    }

    // Yab Coins

    public static void addYabCoins(final long coins) {
        final long current = getYabCoins();
        final long currentTotal = getTotalYabCoins();

        final long coinsWithBonus = (long) (coins * getYabCoinsBonusMultiplier());
        preferencesEdit.putLong(YAB_COINS, current + coinsWithBonus).apply();
        preferencesEdit.putLong(TOTAL_YAB_COINS, currentTotal + coinsWithBonus).apply();
    }

    public static void spendYabCoins(final long coins) {
        final long current = getYabCoins();
        preferencesEdit.putLong(YAB_COINS, current - coins)
                .apply();
    }

    private static void setTotalYabCoins(final long coins) {
        preferencesEdit.putLong(TOTAL_YAB_COINS, coins).apply();
    }

    public static long getTotalYabCoins() {
        return preferences.getLong(TOTAL_YAB_COINS, 0);
    }

    private static void setYabCoins(final long coins) {
        preferencesEdit.putLong(YAB_COINS, coins).apply();
    }

    public static long getYabCoins() {
        return preferences.getLong(YAB_COINS, 0);
    }

    public static void setYabCoinsBonusMultiplier(final float bonusMultiplier) {
        preferencesEdit
                .putFloat(YAB_COINS_BONUS_MULTIPLIER, bonusMultiplier)
                .apply();
    }

    public static void resetYabCoinsBonusMultiplier() {
        preferencesEdit
                .putFloat(YAB_COINS_BONUS_MULTIPLIER, INITIAL_YAB_COINS_BONUS_MULTIPLIER)
                .apply();
    }

    public static boolean isYabCoinsBonusMultiplierActive() {
        return getYabCoinsBonusMultiplier() != INITIAL_EXPERIENCE_POINTS_BONUS_MULTIPLIER;
    }

    public static float getYabCoinsBonusMultiplier() {
        return preferences
                .getFloat(YAB_COINS_BONUS_MULTIPLIER, INITIAL_YAB_COINS_BONUS_MULTIPLIER);
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

    // Challenges

    private static void setAppleChallengeCount(final long points) {
        preferencesEdit.putLong(APPLE_CHALLENGES, points).apply();
    }

    public static long getAppleChallengeCount() {
        return preferences.getLong(APPLE_CHALLENGES, 0);
    }

    public static void increaseAppleChallengeCount() {
        long current = getAppleChallengeCount();
        preferencesEdit.putLong(APPLE_CHALLENGES, current + 1).apply();
    }

    private static void setPearChallengeCount(final long points) {
        preferencesEdit.putLong(PEAR_CHALLENGES, points).apply();
    }

    public static long getPearChallengeCount() {
        return preferences.getLong(PEAR_CHALLENGES, 0);
    }

    public static void increasePearChallengeCount() {
        long current = getPearChallengeCount();
        preferencesEdit.putLong(PEAR_CHALLENGES, current + 1).apply();
    }

    private static void setPlumChallengeCount(final long points) {
        preferencesEdit.putLong(PLUM_CHALLENGES, points).apply();
    }

    public static long getPlumChallengeCount() {
        return preferences.getLong(PLUM_CHALLENGES, 0);
    }

    public static void increasePlumChallengeCount(){
        long current = getPlumChallengeCount();
        preferencesEdit.putLong(PLUM_CHALLENGES, current +1).apply();
    }

    private static void setFruitChallengeCount(final long points) {
        preferencesEdit.putLong(FRUIT_CHALLENGES, points).apply();
    }

    public static long getFruitChallengeCount() {
        return preferences.getLong(FRUIT_CHALLENGES, 0);
    }

    public static void increaseFruitChallengeCount() {
        long current = getFruitChallengeCount();
        preferencesEdit.putLong(FRUIT_CHALLENGES, current+1);
    }

}
