 package com.RadmanHayati.applicationcoroutines.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.homecat.myapplicationcoroutines.R
import com.homecat.myapplicationcoroutines.databinding.ItemsAddressUserBinding
import com.homecat.myapplicationcoroutines.model.Model_Address

class Adapter_Address(val listpost: List<Model_Address>, val chanage: Changeprice) : RecyclerView.Adapter<Adapter_Address.viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.items_address_user,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listpost.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var data = listpost[position]
        holder.binding.post=data
        holder.itemView.setOnClickListener {
            chanage.changecount(data.id)
        }
    }

    interface Changeprice{
        fun changecount(id:String)
    }


    class viewholder(val binding : ItemsAddressUserBinding) : RecyclerView.ViewHolder(binding.root)

}