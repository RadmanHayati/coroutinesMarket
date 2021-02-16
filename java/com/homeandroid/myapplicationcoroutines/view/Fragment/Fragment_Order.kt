package com.RadmanHayati.applicationcoroutines.view.Fragment

import android.content.Intent
import android.net.Uri
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
import com.homeandroid.myapplicationcoroutines.databinding.FragmentOrderBinding
import com.homeandroid.myapplicationcoroutines.databinding.FragmentProfileBinding
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_Cart
import com.homeandroid.myapplicationcoroutines.view.Adapter.Adapter_pay
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_Cart
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_Order
import com.homeandroid.myapplicationcoroutines.viewmodel.Viewmodel_login
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.recyclerview
import java.util.ArrayList

class Fragment_Order : Fragment() {
    var binding : FragmentOrderBinding? = null
    lateinit var  navController : NavController
    var linkprice :String?=null
    var codeprice :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding ?: run {
            binding=  FragmentOrderBinding.inflate(inflater,container,false)
            navController= Navigation.findNavController(requireActivity(),R.id.fragment)
            var viewmodel = ViewModelProvider(this).get(Viewmodel_Order::class.java)
            var userid= Respositry.shardread(requireActivity())
            viewmodel.Listpost(userid,requireArguments().getString("address").toString())
            viewmodel.Mutable_Order.observe(requireActivity(), Observer {
                   Tv_price_all.text=it.price + " تومان "
                   codeprice=it.code
            })

            viewmodel.Mutable_paylist.observe(requireActivity(), Observer {

                recyclerview.layoutManager=LinearLayoutManager(requireActivity())
                val rgselectarray :IntArray= IntArray(it.size)
                val adapter = Adapter_pay(rgselectarray,requireActivity(),it,object  : Adapter_pay.ChangeItemspay{
                    override fun changecount(link: String) {
                        linkprice=link
                    }


                })
                recyclerview.adapter=adapter
            })

            binding!!.RelPrice.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.also {
                    it.data= Uri.parse(linkprice+codeprice)
                }
                startActivity(intent)
            }


        }
        return binding!!.root
    }


}