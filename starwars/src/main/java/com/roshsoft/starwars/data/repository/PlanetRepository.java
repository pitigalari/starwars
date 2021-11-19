package com.roshsoft.starwars.data.repository;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.data.http.Planets;
import com.roshsoft.starwars.data.http.RestService;

import javax.inject.Inject;

import io.reactivex.Single;

public class PlanetRepository implements IPlanetRepository {

    private RestService mRestService;

    @Inject
    public PlanetRepository(RestService restService) {
        mRestService = restService;
    }

    @Override
    public Single<Planets> getPlanets(int page) {
        return mRestService.getAllPlanets((page + ""));
    }

    @Override
    public Single<PlanetDetail> getPlanetDetail(int id) {
        return mRestService.getPlanet(id);
    }
}
