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
import com.homeandroid.myapplicationcoroutines.databinding.FragmentLoginBinding
import com.homeandroid.myapplicationcoroutines.databinding.FragmentPostBinding
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Post
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_fragment_post
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_login
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_post.*

class Fragment_Login : Fragment()  {
    var binding : FragmentLoginBinding? = null
      lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentLoginBinding.inflate(inflater,container,false)
        navController=Navigation.findNavController(requireActivity(),R.id.fragment)
        var viewmodel = ViewModelProvider(this).get(Viewmodel_login::class.java)
        binding!!.data=viewmodel
        viewmodel.mutablelivedata.observe(requireActivity(), Observer {
              if(it.status.equals("ok")){
                  Respositry.shardwrite(requireContext(),it.user_id)
                  navController.navigate(R.id.action_fragment_Login_to_fragment_profile)
              }else
              {
                  Toast.makeText(requireContext(), "خطا نام کاربری یا رمز عبور صحیح نیست!", Toast.LENGTH_SHORT).show()
              }
        })
        viewmodel.mutable_checkintent.observe(requireActivity(), Observer {
            navController.navigate(R.id.action_fragment_Login_to_fragment_Register)
        })

        return binding!!.root
    }

}