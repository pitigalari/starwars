package com.roshsoft.starwars;

import com.roshsoft.starwars.di.AppComponent;
import com.roshsoft.starwars.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class StarWarsApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
        return appComponent;
    }
}
