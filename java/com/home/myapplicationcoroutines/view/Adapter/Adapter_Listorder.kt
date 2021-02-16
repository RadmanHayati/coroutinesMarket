package com.RadmanHayati.applicationcoroutines.view.Adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.homecat.myapplicationcoroutines.R
import com.homecat.myapplicationcoroutines.databinding.ItemsorderBinding
import com.homecat.myapplicationcoroutines.databinding.ItemspostBinding
import com.homecat.myapplicationcoroutines.model.Model_Listorder
import com.homecat.myapplicationcoroutines.model.Model_Post

class Adapter_Listorder(val listpost : List<Model_Listorder>) : RecyclerView.Adapter<Adapter_Listorder.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.itemsorder,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listpost.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var data = listpost[position]
        holder.binding.post=data
        if(data.status.equals("1")){
            holder.binding.LinerNotprice.visibility=View.GONE
            holder.binding.LinerSuccess.visibility=View.VISIBLE
        }else
        {
            holder.binding.LinerNotprice.visibility=View.VISIBLE
            holder.binding.LinerSuccess.visibility=View.GONE
        }
    }

    interface Clickitems{
        fun itemid(id:String)
    }

    class viewholder(val binding : ItemsorderBinding) : RecyclerView.ViewHolder(binding.root)

}