package com.roshsoft.starwars;

import com.roshsoft.starwars.di.AppComponent;
import com.roshsoft.starwars.di.DaggerAppComponent;

import org.jetbrains.annotations.NotNull;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class StarWarsApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected void log(int priority, String tag, @NotNull String message, Throwable t) {
                super.log(priority, tag, message, t);
            }
        });
    }
}
