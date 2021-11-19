package com.roshsoft.starwars.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.data.http.Planets;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PlanetsPagingDataSource extends PageKeyedDataSource<Integer, PlanetDetail> {

    private final IPlanetRepository mPlanetRepo;
    private final CompositeDisposable mCompositeDisposable;

    public PlanetsPagingDataSource(IPlanetRepository planetRepository) {
        mPlanetRepo = planetRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadInitial(@NonNull @NotNull LoadInitialParams<Integer> params, @NonNull @NotNull LoadInitialCallback<Integer, PlanetDetail> callback) {
        mCompositeDisposable.add(mPlanetRepo.getPlanets(1)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Planets>() {
                    @Override
                    public void onSuccess(@NotNull Planets planets) {
                        List<PlanetDetail> planetList = planets.getPlanetList();
                        if (planetList != null && !planetList.isEmpty()) {
                            callback.onResult(planetList,
                                    planets.getPreviousPage(),
                                    planets.getNextPage());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Timber.e(e);
                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, PlanetDetail> callback) {
    }

    @Override
    public void loadAfter(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, PlanetDetail> callback) {
        mCompositeDisposable.add(mPlanetRepo.getPlanets(params.key)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Planets>() {
                    @Override
                    public void onSuccess(@NotNull Planets planets) {
                        List<PlanetDetail> planetList = planets.getPlanetList();
                        if (planetList != null && !planetList.isEmpty()) {
                            callback.onResult(planetList, planets.getNextPage());
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
