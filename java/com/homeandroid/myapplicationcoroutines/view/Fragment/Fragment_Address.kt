package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeandroid.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Respositry
import com.homeandroid.myapplicationcoroutines.databinding.FragmentAddressBinding
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Address
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Cart
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_GetAddress
import kotlinx.android.synthetic.main.fragment_post.recyclerview

class Fragment_Address : Fragment() {
    var binding : FragmentAddressBinding? = null
    lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentAddressBinding.inflate(inflater,container,false)
        navController= Navigation.findNavController(requireActivity(),R.id.fragment)
        var viewmodel = ViewModelProvider(this).get(Viewmodel_GetAddress::class.java)
        var userid= Respositry.shardread(requireActivity())
        viewmodel.Listpost(userid)

        viewmodel.mutableaddress.observe(requireActivity(), Observer {
            recyclerview.layoutManager=LinearLayoutManager(requireActivity())
            val adapter = Adapter_Address(it,object  : Adapter_Address.Changeprice{
                override fun changecount(id: String) {
                    val bundle = Bundle()
                    bundle.putString("address",id)
                    navController.navigate(R.id.action_fragment_Address_to_fragment_Order,bundle)
                }


            })
          recyclerview.adapter=adapter
        })

        return binding!!.root
    }


}