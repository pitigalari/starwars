package com.roshsoft.starwars.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.roshsoft.starwars.data.http.Planet;
import com.roshsoft.starwars.data.http.PlanetsApiResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PlanetsPagingDataSource extends PageKeyedDataSource<Integer, Planet> {

    private final IPlanetRepository mPlanetRepo;
    private final CompositeDisposable mCompositeDisposable;

    public PlanetsPagingDataSource(IPlanetRepository planetRepository) {
        mPlanetRepo = planetRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadInitial(@NonNull @NotNull LoadInitialParams<Integer> params, @NonNull @NotNull LoadInitialCallback<Integer, Planet> callback) {
        mCompositeDisposable.add(mPlanetRepo.getPlanets(1)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<PlanetsApiResponse>() {
                    @Override
                    public void onSuccess(@NotNull PlanetsApiResponse planetsApiResponse) {
                        List<Planet> planetList = planetsApiResponse.getPlanetList();
                        if (planetList != null && !planetList.isEmpty()) {
                            callback.onResult(planetList,
                                    planetsApiResponse.getPreviousPage(),
                                    planetsApiResponse.getNextPage());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Timber.e(e);
                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, Planet> callback) {
    }

    @Override
    public void loadAfter(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, Planet> callback) {
        mCompositeDisposable.add(mPlanetRepo.getPlanets(params.key)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<PlanetsApiResponse>() {
                    @Override
                    public void onSuccess(@NotNull PlanetsApiResponse planetsApiResponse) {
                        List<Planet> planetList = planetsApiResponse.getPlanetList();
                        if (planetList != null && !planetList.isEmpty()) {
                            callback.onResult(planetList, planetsApiResponse.getNextPage());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Timber.e(e);
                    }
                }));
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }
}
