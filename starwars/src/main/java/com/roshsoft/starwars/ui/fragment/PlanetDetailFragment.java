package com.roshsoft.starwars.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.roshsoft.starwars.data.viewmodel.PlanetDetailViewModel;
import com.roshsoft.starwars.databinding.FragmentPlanetDetailBinding;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import timber.log.Timber;

public class PlanetDetailFragment extends BaseFragment<FragmentPlanetDetailBinding> {

    public static final String TAG = "PlanetDetailFragment";

    @Inject
    PlanetDetailViewModel planetDetailViewModel;

    private TextView mName, mOrbitalPeriod, mGravity;
    private ProgressBar mLoadingBar;
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(FragmentPlanetDetailBinding.inflate(inflater, container, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        planetDetailViewModel.getPlanetDetailMutableLiveData().observe(getViewLifecycleOwner(),
                planetDetail -> {
                    mName.setText(planetDetail.getName());
                    mOrbitalPeriod.setText(planetDetail.getOrbitalPeriod());
                    mGravity.setText(planetDetail.getGravity());
                    mLoadingBar.setVisibility(View.GONE);
                    Glide.with(PlanetDetailFragment.this)
                            .load((planetDetail.getImageUrl())).into(image);
                });

        try {
            planetDetailViewModel.fetchPlanetDetail(getArguments().getInt("id", 0));
        } catch (Exception e) {
            Timber.e(e);
        }
    }

    @Override
    protected void bindViews(FragmentPlanetDetailBinding binding) {
        mName = binding.name;
        mGravity = binding.gravity;
        mOrbitalPeriod = binding.obitalPeriod;
        image = binding.image;
        mLoadingBar = binding.loading;
    }

    @Override
    protected void unbindViews() {
    }
}
