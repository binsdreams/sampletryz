package com.example.advancedagger2.di.auth

import androidx.lifecycle.ViewModel
import com.bins.tryz.di.ViewModelKey
import com.bins.tryz.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    /**
     * Binds the auth view model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing MainViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
}