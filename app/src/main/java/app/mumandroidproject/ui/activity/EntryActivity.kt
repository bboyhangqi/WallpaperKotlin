package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast

import app.mumandroidproject.R
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.helper.PermissionHelp
import app.mumandroidproject.ui.fragment.LocalFragment
import app.mumandroidproject.ui.fragment.CategoryFragment
import app.mumandroidproject.ui.fragment.HotFragment
import kotlinx.android.synthetic.main.activity_entry.*
import kotlinx.android.synthetic.main.navigation_bar.*

class EntryActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    private val TAG = "EntryActivity"

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var currentPage = Constant.HOME_PAGE_ID.PAGE_HOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.setOnPageChangeListener(this)
        PermissionHelp.checkPermission(this)
        initNavigationBar()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        Log.d(TAG,"zhq.debug onPageScrollStateChanged position: $position")
        when (position) {
            0 ->  currentPage = Constant.HOME_PAGE_ID.PAGE_HOT
            1 ->  currentPage = Constant.HOME_PAGE_ID.PAGE_CATEGORY
            2 ->  currentPage = Constant.HOME_PAGE_ID.PAGE_LOCAL
        }
        updateNavigations()
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            Log.d("SectionsPagerAdapter", position.toString())
            when (position) {
                0 -> return HotFragment.instance
                1 -> return CategoryFragment.instance
                2 -> return LocalFragment.instance
            }
            return null
        }

        override fun getCount(): Int {
            return 3
        }
    }

    private fun initNavigationBar() {
        iv_nvi_hot.setOnClickListener { onHotSelected() }
        iv_nvi_local.setOnClickListener { onLocalSelected() }
        iv_nvi_category.setOnClickListener { onCategorySeleted() }
        currentPage = Constant.HOME_PAGE_ID.PAGE_HOT
        updateNavigations()
    }

    private fun updateNavigations(){
        when(currentPage){
            Constant.HOME_PAGE_ID.PAGE_HOT -> {
                iv_nvi_hot.setImageResource(R.drawable.hot_btn_p)
                iv_nvi_local.setImageResource(R.drawable.search_btn)
                iv_nvi_category.setImageResource(R.drawable.category_btn)
            }
            Constant.HOME_PAGE_ID.PAGE_CATEGORY -> {
                iv_nvi_category.setImageResource(R.drawable.category_btn_p)
                iv_nvi_local.setImageResource(R.drawable.search_btn)
                iv_nvi_hot.setImageResource(R.drawable.hot_btn)
            }
            Constant.HOME_PAGE_ID.PAGE_LOCAL -> {
                iv_nvi_local.setImageResource(R.drawable.search_btn_p)
                iv_nvi_hot.setImageResource(R.drawable.hot_btn)
                iv_nvi_category.setImageResource(R.drawable.category_btn)
            }
        }
    }

    private fun onHotSelected() {
        Log.d(TAG, "onHotSelected")
        container.setCurrentItem(0, true)
    }

    private fun onCategorySeleted() {
        Log.d(TAG, "onCategorySeleted")
        container.setCurrentItem(1, true)
    }

    private fun onLocalSelected() {
        Log.d(TAG, "onLocalSelected")
        container.setCurrentItem(2, true)
    }

    private var backPressedBlock = true
    override fun onBackPressed() {
        if (backPressedBlock) {
            backPressedBlock = false
            Handler().postDelayed({ backPressedBlock = true }, 1000)
            Toast.makeText(this, "Please press again to leave", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }


}
