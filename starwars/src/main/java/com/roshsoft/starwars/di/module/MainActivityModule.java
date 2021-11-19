package com.roshsoft.starwars.di.module;

import com.roshsoft.starwars.ui.fragment.BaseNavHostFragment;
import com.roshsoft.starwars.ui.fragment.PlanetsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract BaseNavHostFragment providesBaseNavHostFragment();

    @ContributesAndroidInjector
    abstract PlanetsFragment providesPlanetsFragment();
}
