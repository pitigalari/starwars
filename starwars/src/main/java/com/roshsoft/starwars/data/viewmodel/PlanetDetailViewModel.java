package com.roshsoft.starwars.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roshsoft.starwars.data.http.PlanetDetail;
import com.roshsoft.starwars.data.repository.IPlanetRepository;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PlanetDetailViewModel extends ViewModel {

    private final IPlanetRepository mPlanetRepository;

    private MutableLiveData<PlanetDetail> mPlanetDetailMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public PlanetDetailViewModel(IPlanetRepository planetRepository) {
        mPlanetRepository = planetRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void fetchPlanetDetail(int id) {
        mCompositeDisposable.add(mPlanetRepository.getPlanetDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PlanetDetail>() {
                    @Override
                    public void onSuccess(@NotNull PlanetDetail planetDetail) {
                        mPlanetDetailMutableLiveData.setValue(planetDetail);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Timber.e(e);
                    }
                }));
    }

    public LiveData<PlanetDetail> getPlanetDetailMutableLiveData() {
        return mPlanetDetailMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        try {
            mCompositeDisposable.dispose();
        } catch (Exception ignored) {
        }
    }
}
