package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homeandroid.myapplicationcoroutines.model.Model_Post
import kotlinx.coroutines.Job

class Viewmodel_fragment_post : ViewModel() {

    lateinit var job : Job
    val mutablelivedata = MutableLiveData<List<Model_Post>>()

    fun Listpost(){
        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Listpost())
        },
         {
            mutablelivedata.value=it
        })
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}