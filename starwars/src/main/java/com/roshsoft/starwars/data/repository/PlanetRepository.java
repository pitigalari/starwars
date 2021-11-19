package com.roshsoft.starwars.data.repository;

import com.roshsoft.starwars.data.http.PlanetsApiResponse;
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
    public Single<PlanetsApiResponse> getPlanets(int page) {
        return mRestService.getAllPlanets((page + ""));
    }
}
