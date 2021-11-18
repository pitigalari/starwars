package com.roshsoft.starwars.ui.base;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import dagger.android.support.DaggerFragment;


public abstract class BaseFragment<VB extends ViewBinding> extends DaggerFragment {

    private BaseActivity mActivity;
    private VB mBinding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindViews();
        mBinding = null;
    }

    protected final BaseActivity getBaseActivity() {
        return mActivity;
    }

    public final VB getBinding() {
        return mBinding;
    }

    protected abstract void bindViews(VB binding);

    protected abstract void unbindViews();

    public final View onCreateView(VB binding) {
        mBinding = binding;
        bindViews(mBinding);
        return binding.getRoot();
    }
}
