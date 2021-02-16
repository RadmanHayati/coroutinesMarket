package com.RadmanHayati.applicationcoroutines.view.Activity

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.homecat.myapplicationcoroutines.R
import com.RadmanHayati.applicationcoroutines.Respositry
import com.homecat.myapplicationcoroutines.viewmodel.Viewmodel_Cart
import com.homecat.myapplicationcoroutines.viewmodel.Viewmodel_Maincount
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var navController: NavController
    lateinit var appbar: AppBarConfiguration
     var user:String?=null
     val Badge :   BadgeDrawable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        appbar = AppBarConfiguration.Builder(R.navigation.navagation).build()
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appbar)
        NavigationUI.setupWithNavController(bottom, navController)
        bottom.setOnNavigationItemSelectedListener(this)
        user= Respositry.shardread(this)
        Get_Number()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                navController.navigate(R.id.fragment_Post)
            }

            R.id.menu_cart -> {
                Get_Number()
//                Badge!!.isVisible=false
                var userid= Respositry.shardread(applicationContext)
                if(userid.equals("notfind")){
                    navController.navigate(R.id.fragment_Login)
                }else
                {
                    navController.navigate(R.id.fragment_Cart)
                }

            }
            R.id.menu_profile -> {
               var userid= Respositry.shardread(applicationContext)
                if(userid.equals("notfind")){
                    navController.navigate(R.id.fragment_Login)
                }else
                {
                    navController.navigate(R.id.fragment_profile)
                }

            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null as DrawerLayout)
    }

    fun Get_Number(){
        var viewmodel = ViewModelProvider(this).get(Viewmodel_Maincount::class.java)
        viewmodel.Listpost(user!!)
        viewmodel.mutablecount.observe(this, Observer {
          val Badge :   BadgeDrawable = bottom.getOrCreateBadge(R.id.menu_cart)
            Badge.backgroundColor=Color.RED
            Badge.number=it.count.toInt()
        })


    }

}
