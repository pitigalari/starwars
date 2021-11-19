package com.roshsoft.starwars.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.roshsoft.starwars.data.http.PlanetDetail;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PlanetsDataSourceFactory extends DataSource.Factory<Integer, PlanetDetail> {

    private final IPlanetRepository mPlanetRepository;

    private PlanetsPagingDataSource dataSource;

    @Inject
    public PlanetsDataSourceFactory(IPlanetRepository planetRepository) {
        mPlanetRepository = planetRepository;
    }

    @NonNull
    @NotNull
    @Override
    public DataSource<Integer, PlanetDetail> create() {
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
