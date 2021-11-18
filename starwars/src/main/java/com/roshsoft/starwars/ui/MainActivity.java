package com.roshsoft.starwars.ui;

import android.os.Bundle;

import com.roshsoft.starwars.R;
import com.roshsoft.starwars.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}