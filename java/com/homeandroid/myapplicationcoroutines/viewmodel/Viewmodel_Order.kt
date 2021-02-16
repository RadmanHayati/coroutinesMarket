package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homeandroid.myapplicationcoroutines.model.*
import kotlinx.coroutines.Job

class Viewmodel_Order : ViewModel() {

    lateinit var job : Job
    val Mutable_paylist = MutableLiveData<List<Model_Pay>>()
    val Mutable_Order = MutableLiveData<Model_Order>()

    fun Listpost(user:String,address:String){

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Listpay())
        },
         {
             Mutable_paylist.value=it
        })

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_Order(user,address))
        },
            {
                Mutable_Order.value=it
            })
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}