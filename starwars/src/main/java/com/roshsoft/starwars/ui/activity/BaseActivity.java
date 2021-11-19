package com.roshsoft.starwars.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    private VB mBinding;

    protected final View onCreate(VB binding) {
        mBinding = binding;
        View view = mBinding.getRoot();
        setContentView(view);
        bindViews(mBinding);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindViews();
        mBinding = null;
    }

    public final VB getBinding() {
        return mBinding;
    }

    protected abstract void bindViews(VB binding);

    protected abstract void unbindViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
