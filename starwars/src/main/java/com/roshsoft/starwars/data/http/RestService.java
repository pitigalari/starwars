package com.roshsoft.starwars.data.http;

import com.roshsoft.starwars.data.Constants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    @GET(Constants.ENDPOINT_ALL_PLANETS)
    Single<Planets> getAllPlanets(@Query("page") String page);

    @GET(Constants.ENDPOINT_PLANET)
    Single<PlanetDetail> getPlanet(@Path("id") int id);
}
