package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.util.Log

import app.mumandroidproject.R
import app.mumandroidproject.ui.fragment.LocalFragment
import app.mumandroidproject.ui.fragment.CategoryFragment
import app.mumandroidproject.ui.fragment.HotFragment
import kotlinx.android.synthetic.main.activity_entry.*

class EntryActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            Log.d("SectionsPagerAdapter",position.toString())
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


}
