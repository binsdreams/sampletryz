package com.bins.tryz.ui.main

import androidx.lifecycle.MutableLiveData
import com.bins.base.BaseViewModel
import com.bins.domain.usecases.SquireRepoUseCase
import com.bins.tryz.entity.Data
import com.bins.tryz.entity.SquireRepo
import com.bins.tryz.mapper.DomainToPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var useCase : SquireRepoUseCase) : BaseViewModel(){


    private val mapper  =DomainToPresentationMapper()
    private var dataList = MutableLiveData<Data<List<SquireRepo>>>()

    fun getSquireRepos() {

        launch {
            val deviceInfo = useCase.getSquireRepos()
            deviceInfo.consumeEach { response ->
                val mappedResponse = mapper.mapTo(response)
                withContext(Dispatchers.Main) {
                    dataList.postValue(mappedResponse)
                }
            }
        }
    }

    fun getData() =dataList

    fun getEmptyListForShimmer():List<SquireRepo?>{
        val arrayList = ArrayList<SquireRepo?>(10)
        for (i in 0..10){
            arrayList.add(null)
        }
        return arrayList
    }

}
