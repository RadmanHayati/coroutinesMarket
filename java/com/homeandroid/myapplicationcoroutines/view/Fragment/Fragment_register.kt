package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.homeandroid.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Respositry
import com.homeandroid.myapplicationcoroutines.databinding.FragmentRegisterBinding
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_register
import kotlinx.android.synthetic.main.fragment_post.*

class Fragment_register : Fragment() {
    var binding : FragmentRegisterBinding? = null
    lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentRegisterBinding.inflate(inflater,container,false)
        navController=Navigation.findNavController(requireActivity(),R.id.fragment)
        var viewmodel = ViewModelProvider(this).get(Viewmodel_register::class.java)
        binding!!.data=viewmodel
        viewmodel.mutablelivedata.observe(requireActivity(), Observer {
              if(it.status.equals("ok")){
                  Respositry.shardwrite(requireContext(),it.user_id)
                  navController.navigate(R.id.action_fragment_Register_to_fragment_profile)
              }else
              {
                  Toast.makeText(requireContext(), " خطا در ثبت نام اطلاعات...!", Toast.LENGTH_SHORT).show()
              }
        })
        viewmodel.mutable_checkintent.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragment_Register_to_fragment_Login)

        })

        return binding!!.root
    }

}