package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.*
import kotlinx.coroutines.Job

class Viewmodel_Cart : ViewModel() {

    lateinit var job : Job
    val Mutable_cartlist = MutableLiveData<List<Model_Listcart>>()
    val Mutable_price_All = MutableLiveData<Price>()

    fun Listpost(user:String){

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_ListCart(user))
        },
         {
             Mutable_cartlist.value=it
        })

        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Post_Price_All(user))
        },
            {
                Mutable_price_All.value=it
            })
    }

    override fun onCleared() {
        if(::job.isInitialized)job.cancel()
        super.onCleared()
    }

}