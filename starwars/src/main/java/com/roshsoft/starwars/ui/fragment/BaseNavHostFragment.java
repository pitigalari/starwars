package com.roshsoft.starwars.ui.fragment;

import android.content.Context;

import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;

public class BaseNavHostFragment extends NavHostFragment implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> childFragmentInjector;

    @Override
    public void onAttach(@NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return childFragmentInjector;
    }
}
