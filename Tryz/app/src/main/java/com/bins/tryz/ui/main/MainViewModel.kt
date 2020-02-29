package com.bins.tryz.ui.main

import androidx.lifecycle.ViewModel
import com.bins.domain.usecases.SquireRepoUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private var useCase : SquireRepoUseCase) : ViewModel(){
}
