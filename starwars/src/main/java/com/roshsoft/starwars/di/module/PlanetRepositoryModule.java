package com.roshsoft.starwars.di.module;

import com.roshsoft.starwars.data.http.RestService;
import com.roshsoft.starwars.data.repository.IPlanetRepository;
import com.roshsoft.starwars.data.repository.PlanetRepository;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class PlanetRepositoryModule {

    @Provides
    public static IPlanetRepository providePlanetRepository(RestService restService) {
        return new PlanetRepository(restService);
    }
}
