package com.RadmanHayati.applicationcoroutines.view.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.homeandroid.myapplicationcoroutines.R
import com.homeandroid.myapplicationcoroutines.databinding.ItemsAddressUserBinding
import com.homeandroid.myapplicationcoroutines.databinding.ItemspayBinding
import com.homeandroid.myapplicationcoroutines.model.Model_Address
import com.homeandroid.myapplicationcoroutines.model.Model_Pay

class Adapter_pay(val array:IntArray,val activity:Activity,val listpost: List<Model_Pay>, val chanage: ChangeItemspay) : RecyclerView.Adapter<Adapter_pay.viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.itemspay,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listpost.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val id=View.generateViewId()
        holder.binding.RgPay.id=id
        array[position]=id
        var data = listpost[position]
        holder.binding.post=data
        if(position==1){
            holder.binding.RgPay.isChecked=true
            chanage.changecount(data.link)
        }
        holder.itemView.setOnClickListener {
            chanage.changecount(data.link)
        }
        holder.binding.RgPay.setOnClickListener {v->
            chanage.changecount(data.link)
            for(i  in array.indices){
               val rg = activity.findViewById<RadioButton>(array[i])
                rg.isChecked=v.id==array[i]
            }
        }
    }

    interface ChangeItemspay{
        fun changecount(link:String)
    }


    class viewholder(val binding : ItemspayBinding) : RecyclerView.ViewHolder(binding.root)

}