package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeandroid.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Respositry
import com.homeandroid.myapplicationcoroutines.databinding.FragmentCartBinding
import com.homeandroid.myapplicationcoroutines.databinding.FragmentProfileBinding
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Cart
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_Cart
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_login
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.recyclerview
import java.util.ArrayList

class Fragment_Cart : Fragment() {
    var binding : FragmentCartBinding? = null
    lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding ?: run {
            binding=  FragmentCartBinding.inflate(inflater,container,false)
            navController= Navigation.findNavController(requireActivity(),R.id.fragment)
            var viewmodel = ViewModelProvider(this).get(Viewmodel_Cart::class.java)
            var userid= Respositry.shardread(requireActivity())
            viewmodel.Listpost(userid)
            viewmodel.Mutable_price_All.observe(requireActivity(), Observer {
                Tv_price_all.text=it.price +  " تومان "
            })

            viewmodel.Mutable_cartlist.observe(requireActivity(), Observer {
                val arralist = ArrayList(it)
                recyclerview.layoutManager=LinearLayoutManager(requireActivity())
                val adapter = Adapter_Cart(requireActivity(),arralist,userid,object  : Adapter_Cart.Changeprice{
                    override fun changecount() {
                        viewmodel.Listpost(userid)
                        viewmodel.Mutable_price_All.observe(requireActivity(), Observer {
                            Tv_price_all.text=it.price +  " تومان "
                        })
                    }

                })
                recyclerview.adapter=adapter
            })

            binding!!.RelPrice.setOnClickListener {
                navController.navigate(R.id.action_fragment_Cart_to_fragment_Address)
            }


        }
        return binding!!.root
    }


}