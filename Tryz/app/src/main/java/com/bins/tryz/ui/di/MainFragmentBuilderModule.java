package com.bins.tryz.ui.di;

import android.app.Activity;

import com.bins.tryz.di.scopes.FragmentScoped;
import com.bins.tryz.ui.main.MainFragment;
import com.example.advancedagger2.di.auth.MainModule;
import com.example.advancedagger2.di.auth.MainViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This Class {@linkplain MainFragmentBuilderModule} is responsible for for android injection
 * for the activity with in the application to avoid the seprate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection}
 *
 * {@link MainViewModelModule} can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */
@Module
public abstract class MainFragmentBuilderModule {

    /**
     * Automatically create sub-component
     *
     * @return
     */
    @FragmentScoped
    @ContributesAndroidInjector(
            modules = { MainViewModelModule.class, MainModule.class}
    )
    abstract MainFragment contributeMainFragment();
}
