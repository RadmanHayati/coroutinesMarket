package com.RadmanHayati.applicationcoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object ThridMain {
    fun <T:Any> Coroutines_Handel(Api : suspend(()->T),callback : ( (T)->Unit ) )=
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt Api()
            }.await()
            callback(data)
        }
}