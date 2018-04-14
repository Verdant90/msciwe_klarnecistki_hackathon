package com.szymon.hackathonapplication.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.szymon.hackathonapplication.R;

public class ShopActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
//        ButterKnife.bind(this);
    }

}
