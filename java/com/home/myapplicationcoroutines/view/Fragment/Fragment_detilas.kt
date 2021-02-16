package com.RadmanHayati.applicationcoroutines.view.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.homecat.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Api
import com.RadmanHayati.applicationcoroutines.Handel_Requst
import com.RadmanHayati.applicationcoroutines.Respositry
import com.RadmanHayati.applicationcoroutines.ThridMain
import com.homecat.myapplicationcoroutines.databinding.FragmentDetialsBinding
import com.homecat.myapplicationcoroutines.model.Post
import com.homecat.myapplicationcoroutines.model.Slider
import com.homecat.myapplicationcoroutines.view.Activity.MainActivity
import com.homecat.myapplicationcoroutines.view.Adapter.Viewpager_Adapter
import com.homecat.myapplicationcoroutines.viewmodel.Viewmodel_fragment_Detials
import kotlinx.android.synthetic.main.fragment_detials.*
import kotlinx.coroutines.Job

class Fragment_detilas : Fragment() {
    lateinit var binding : FragmentDetialsBinding
    lateinit var job : Job
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.fragment_detials,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = ViewModelProvider(this).get( Viewmodel_fragment_Detials::class.java)
        viewmodel.Listpost(arguments?.getString("idpost")!!)
        viewmodel.mutablelivedata.observe(requireActivity(), Observer {
            Viewpager(it.slider)
            val post = Post(it.post[0].date,it.post[0].des,it.post[0].id,it.post[0].imageurl,it.post[0].price,it.post[0].title,it.post[0].view)
            binding.postdetials=post
            Btn_addcart.text=" افزودن به سبد خرید "+post.price + " تومان "
            Btn_addcart.setOnClickListener {
                var userid= Respositry.shardread(requireActivity())
                job= ThridMain.Coroutines_Handel({
                    Handel_Requst.Reqqiest(Api.invoke().Post_AddCart(post.id,userid,"1","add"))
                },
                    {
                     val main= activity as MainActivity?
                        main!!.Get_Number()
                    })

            }

        })
    }

    fun Viewpager(list : List<Slider>){
        val adapter = Viewpager_Adapter(list)
        viewpager.adapter=adapter
        viewpager.clipToPadding=false
        viewpager.pageMargin=20
        viewpager.setPadding(45,8,10,8)
        viewpager.currentItem=2
    }


}
