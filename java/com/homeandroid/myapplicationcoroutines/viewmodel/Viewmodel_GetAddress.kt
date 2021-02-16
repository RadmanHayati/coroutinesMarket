package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homeandroid.myapplicationcoroutines.model.*
import kotlinx.coroutines.Job

class Viewmodel_GetAddress : ViewModel() {

    lateinit var job : Job
    val mutableaddress = MutableLiveData<List<Model_Address>>()

    fun Listpost(user:String){

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_Address(user))
        },
         {
             mutableaddress.value=it
        })
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}