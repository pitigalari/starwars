package com.roshsoft.starwars.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.roshsoft.starwars.data.http.Planet;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PlanetsDataSourceFactory extends DataSource.Factory<Integer, Planet> {

    private final IPlanetRepository mPlanetRepository;

    private PlanetsPagingDataSource dataSource;

    @Inject
    public PlanetsDataSourceFactory(IPlanetRepository planetRepository) {
        mPlanetRepository = planetRepository;
    }

    @NonNull
    @NotNull
    @Override
    public DataSource<Integer, Planet> create() {
        dataSource = new PlanetsPagingDataSource(mPlanetRepository);
        return dataSource;
    }

    public void dispose() {
        if (dataSource != null) {
            dataSource.dispose();
            dataSource = null;
        }
    }
}
