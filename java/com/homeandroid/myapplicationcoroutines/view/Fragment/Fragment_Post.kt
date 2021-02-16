package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeandroid.myapplicationcoroutines.R
import com.homeandroid.myapplicationcoroutines.databinding.FragmentPostBinding
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Post
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_fragment_post
import kotlinx.android.synthetic.main.fragment_post.*

class Fragment_Post : Fragment(),Adapter_Post.Clickitems {

      var binding : FragmentPostBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            binding=  FragmentPostBinding.inflate(inflater,container,false)
            var viewmodel = ViewModelProvider(this).get(Viewmodel_fragment_post::class.java)
            viewmodel.Listpost()
            viewmodel.mutablelivedata.observe(requireActivity(), Observer {itpost->
                recyclerview.also {
                    it.layoutManager=LinearLayoutManager(requireActivity())
                    val adapter = Adapter_Post(itpost,this)
                    it.adapter=adapter
                }
            })

        return binding!!.root
    }

    override fun itemid(id: String) {
         val bundle= Bundle()
         bundle.putString("idpost",id)
         Navigation.findNavController(recyclerview).navigate(R.id.action_fragment_Post_to_fragment_detilas,bundle)
    }
}