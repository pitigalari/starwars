package com.roshsoft.starwars.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.roshsoft.starwars.databinding.ActivityMainBinding;
import com.roshsoft.starwars.ui.fragment.BaseFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private BaseFragment<?> mCurrentFragment;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(ActivityMainBinding.inflate(getLayoutInflater()));
        openPlanetsFragment();
    }

    public void openPlanetsFragment() {
//        mCurrentFragment = new PlanetsFragment();
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainFrameLayout, mCurrentFragment, PlanetsFragment.TAG)
//                .commit();
    }

    @Override
    protected void bindViews(ActivityMainBinding binding) {
//        frameLayout = binding.mainFrameLayout;
    }

    @Override
    protected void unbindViews() {
    }
}