package com.roshsoft.starwars.di;

import com.roshsoft.starwars.di.module.MainActivityModule;
import com.roshsoft.starwars.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();
}
