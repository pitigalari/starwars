package com.roshsoft.starwars.di;

import com.roshsoft.starwars.StarWarsApplication;
import com.roshsoft.starwars.di.module.AppModule;
import com.roshsoft.starwars.di.module.MainActivityModule;
import com.roshsoft.starwars.di.module.PlanetRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        ActivityBuilder.class,
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class,
        PlanetRepositoryModule.class
})
public interface AppComponent extends AndroidInjector<StarWarsApplication> {

    void inject(StarWarsApplication application);
}
