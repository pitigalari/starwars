package com.roshsoft.starwars.data.repository;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.data.http.Planets;

import io.reactivex.Single;

public interface IPlanetRepository {

    Single<Planets> getPlanets(int page);

    Single<PlanetDetail> getPlanetDetail(int id);
}
