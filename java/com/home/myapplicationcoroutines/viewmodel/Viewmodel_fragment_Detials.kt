package com.RadmanHayati.myapplicationcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.model.Model_Post
import com.homecat.myapplicationcoroutines.model.PostDetials
import kotlinx.coroutines.Job

class Viewmodel_fragment_Detials : ViewModel() {

    lateinit var job : Job
    val mutablelivedata = MutableLiveData<PostDetials>()

    fun Listpost(id:String){
        job= ThridMain.Coroutines_Handel({
            Handel_Requst.Reqqiest(Api.invoke().Postdetials(id))
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