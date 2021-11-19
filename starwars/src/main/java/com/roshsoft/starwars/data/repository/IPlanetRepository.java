package com.roshsoft.starwars.data.repository;

import com.roshsoft.starwars.data.http.PlanetsApiResponse;

import io.reactivex.Single;

public interface IPlanetRepository {

    Single<PlanetsApiResponse> getPlanets(int page);
}
