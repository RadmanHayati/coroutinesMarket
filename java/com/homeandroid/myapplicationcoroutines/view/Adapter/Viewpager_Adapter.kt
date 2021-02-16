package com.RadmanHayati.applicationcoroutines.view.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.homeandroid.myapplicationcoroutines.databinding.ItemsViewpagerBinding
import com.homeandroid.myapplicationcoroutines.model.Slider

class Viewpager_Adapter(val List_Strurl : List<Slider>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return List_Strurl.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemsViewpagerBinding.inflate(LayoutInflater.from(container.context),container,false)
         binding.data=List_Strurl.get(position).image
        container.addView(binding.root)
        return binding.root
    }

    override fun getPageWidth(position: Int): Float {
        return 0.95f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}