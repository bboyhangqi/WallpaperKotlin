package app.mumandroidproject.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * this view contain 3 page3 (Hot Category Local) inside of ViewPager
 * @author MingMingZhao
*/
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        val pageIDs = listOf(R.layout.fragment_hot,R.layout.fragment_category,R.layout.fragment_local)
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var pagerAdapter : SectionPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        pagerAdapter = SectionPagerAdapter()
        container.adapter = pagerAdapter
        container.offscreenPageLimit = 2
    }

    inner class SectionPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = pageIDs.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            if(container.getChildAt(position)!=null) super.instantiateItem(container, position)
            layoutInflater.inflate(pageIDs[position],container,true)
            return container.getChildAt(position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeViewAt(position)
        }
    }


}
