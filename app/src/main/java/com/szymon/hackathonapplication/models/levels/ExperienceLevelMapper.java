package com.szymon.hackathonapplication.models.levels;

import java.util.Arrays;
import java.util.List;

public class ExperienceLevelMapper {

    private static final List<Integer> levelThresholds = Arrays.asList(
            10, 20, 40, 100, 200, 500, 1_000, 2_000, 5_000, 10_000);

    public static int toLevel(final int experience) {
        int level;

        for (level = 0; level < levelThresholds.size(); level++) {
            if (experience < levelThresholds.get(level)) {
                return level;
            }
        }

        return level;
    }

}
