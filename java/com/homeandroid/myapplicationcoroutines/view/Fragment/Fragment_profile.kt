package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeandroid.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Respositry
import com.homeandroid.myapplicationcoroutines.databinding.FragmentLoginBinding
import com.homeandroid.myapplicationcoroutines.databinding.FragmentProfileBinding
import com.homeandroid.myapplicationcoroutines.model.Model_Userinfo
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Listorder
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_login
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_profile
import kotlinx.android.synthetic.main.fragment_post.*

class Fragment_profile : Fragment() {
    var binding : FragmentProfileBinding? = null
    lateinit var  navController : NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentProfileBinding.inflate(inflater,container,false)
        navController= Navigation.findNavController(requireActivity(),R.id.fragment)
        var viewmodel = ViewModelProvider(this).get(Viewmodel_profile::class.java)
        var userid= Respositry.shardread(requireContext())
        viewmodel.Listpost(userid)

        viewmodel.Mutable_userinfo.observe(requireActivity(), Observer {

            val model = Model_Userinfo(it.get(0).email,it.get(0).mobile,it.get(0).name)
            binding!!.data=model

        })
        viewmodel.Mutable_listorder.observe(requireActivity(), Observer {
            recyclerview.layoutManager=LinearLayoutManager(requireActivity())
             val adapter = Adapter_Listorder(it)
            recyclerview.adapter=adapter
        })

        return binding!!.root
    }


}