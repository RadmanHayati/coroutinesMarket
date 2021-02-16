package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.*
import kotlinx.coroutines.Job

class Viewmodel_Maincount : ViewModel() {

    lateinit var job : Job
    val mutablecount = MutableLiveData<_root_ide_package_.com.home.myapplicationcoroutines.model.Count>()

    fun Listpost(user:String){

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_Count_Number_Cart(user))
        },
         {
             mutablecount.value=it
        })
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}