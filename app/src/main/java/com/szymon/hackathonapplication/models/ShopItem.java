package com.szymon.hackathonapplication.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopItem {

    private final String title;
    private final String description;
    private final Long cost;
}
