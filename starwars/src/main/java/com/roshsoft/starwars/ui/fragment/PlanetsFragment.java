package com.roshsoft.starwars.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roshsoft.starwars.data.http.Planet;
import com.roshsoft.starwars.data.viewmodel.PlanetsViewModel;
import com.roshsoft.starwars.databinding.FragmentPlanetsBinding;
import com.roshsoft.starwars.ui.element.PlanetsAdapter;

import javax.inject.Inject;

public class PlanetsFragment extends BaseFragment<FragmentPlanetsBinding> {

    public static final String TAG = "PlanetsFragment";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private PlanetsAdapter planetsAdapter;
    private final Observer<PagedList<Planet>> observer = new Observer<PagedList<Planet>>() {
        @Override
        public void onChanged(PagedList<Planet> planets) {
            if (planetsAdapter != null && planets != null) {
                planetsAdapter.submitList(planets);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    @Inject
    PlanetsViewModel planetsViewModel;

    public PlanetsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(FragmentPlanetsBinding.inflate(inflater, container, false));
        planetsAdapter = new PlanetsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(planetsAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    @Override
    protected void bindViews(FragmentPlanetsBinding binding) {
        recyclerView = binding.recyclerView;
        progressBar = binding.progressBar;
    }

    @Override
    protected void unbindViews() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (planetsViewModel != null)
            planetsViewModel.onCleared();
    }

    private void fetchData() {
        try {
            LiveData<PagedList<Planet>> liveData = planetsViewModel.getPlanetsPagedListLiveData();
            if (liveData != null)
                liveData.removeObserver(observer);
        } catch (Exception ignored) {
        }

        planetsViewModel.fetchPlanets();
        planetsViewModel.getPlanetsPagedListLiveData().observe(getViewLifecycleOwner(), observer);
    }
}