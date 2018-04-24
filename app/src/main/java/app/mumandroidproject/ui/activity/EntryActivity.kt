package app.mumandroidproject.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast

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
        checkPermission()
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

    fun checkPermission() {
        var permissionList = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        var ret = permissionList.filter { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
        if (ret.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList, 1)
        }
    }


    private var backPressedBlock = true
    override fun onBackPressed() {
        if (backPressedBlock) {
            backPressedBlock = false
            Handler().postDelayed({ backPressedBlock = true }, 1000)
            Toast.makeText(this, "Please press again to leave", Toast.LENGTH_SHORT).show()
        }else{
            finish()
        }
    }




}
