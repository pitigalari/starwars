package com.roshsoft.starwars.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.roshsoft.starwars.data.http.Planet;
import com.roshsoft.starwars.data.repository.IPlanetRepository;
import com.roshsoft.starwars.data.repository.PlanetsDataSourceFactory;

import javax.inject.Inject;

public class PlanetsViewModel extends ViewModel {

    private final IPlanetRepository mPlanetRepository;
    private LiveData<PagedList<Planet>> mPlanetsLiveData;
    private PlanetsDataSourceFactory sourceFactory;

    @Inject
    public PlanetsViewModel(IPlanetRepository planetRepository) {
        mPlanetRepository = planetRepository;
    }

    public void fetchPlanets() {
        sourceFactory = new PlanetsDataSourceFactory(mPlanetRepository);
        mPlanetsLiveData = new LivePagedListBuilder<>(sourceFactory,
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPrefetchDistance(5)
                        .build())
                .build();
    }

    public LiveData<PagedList<Planet>> getPlanetsPagedListLiveData() {
        return mPlanetsLiveData;
    }

    @Override
    public void onCleared() {
        super.onCleared();
        if (sourceFactory != null) {
            sourceFactory.dispose();
            sourceFactory = null;
        }
    }
}
