package com.RadmanHayati.applicationcoroutines.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.homecat.myapplicationcoroutines.R
import com.homecat.myapplicationcoroutines.databinding.ItemspostBinding
import com.homecat.myapplicationcoroutines.model.Model_Post

class Adapter_Post(val listpost : List<Model_Post>,val itemsclick : Clickitems) : RecyclerView.Adapter<Adapter_Post.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.itemspost,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listpost.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var data = listpost[position]
        holder.binding.post=data
        holder.binding.TvPrice.text=data.price + " تومان "
        holder.itemView.setOnClickListener {
            itemsclick.itemid(data.id)
        }
    }

    interface Clickitems{
        fun itemid(id:String)
    }

    class viewholder(val binding : ItemspostBinding) : RecyclerView.ViewHolder(binding.root)

}