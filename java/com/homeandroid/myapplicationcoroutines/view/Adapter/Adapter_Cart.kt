package com.RadmanHayati.applicationcoroutines.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.homeandroid.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homeandroid.myapplicationcoroutines.databinding.ItemscartBinding
import com.homeandroid.myapplicationcoroutines.model.Model_Listcart
import kotlinx.coroutines.Job

class Adapter_Cart(val context: Context,val listpost : ArrayList<Model_Listcart>,val userid:String,val chanage : Changeprice) : RecyclerView.Adapter<Adapter_Cart.viewholder>() {
    lateinit var job : Job

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.itemscart,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listpost.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var data = listpost[position]
        holder.binding.post=data
        holder.binding.TvTitle.text=data.title
        holder.binding.TvPrice.text=data.price + " تومان "
        holder.binding.TvCount.text=data.count
        Glide.with(context).load(data.imageurl).into(holder.binding.ImPost)

        holder.binding.ImAdd.setOnClickListener {

            job= ThridMain.Coroutines_Handel({
                Handel_Requst.Reqqiest(Api.invoke().Post_AddCart(data.idproduct,userid,"1","add"))
            },
                {
                    chanage.changecount()
                })

        }

        holder.binding.ImMines.setOnClickListener {

            job= ThridMain.Coroutines_Handel({
                Handel_Requst.Reqqiest(Api.invoke().Post_AddCart(data.idproduct,userid,"1","m"))
            },
                {
                    chanage.changecount()
                })

        }

        holder.binding.BtnDel.setOnClickListener {
            job= ThridMain.Coroutines_Handel({
                Handel_Requst.Reqqiest(Api.invoke().Post_Del_Cart(data.id))
            },
                {
                    listpost.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeRemoved(position,listpost.size)
                    chanage.changecount()
                })
        }
    }

    interface Changeprice{
        fun changecount()
    }


    class viewholder(val binding : ItemscartBinding) : RecyclerView.ViewHolder(binding.root)

}