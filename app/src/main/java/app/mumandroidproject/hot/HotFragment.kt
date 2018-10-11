package app.mumandroidproject.hot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment(), HotView {


    @Inject
    lateinit var hotPresenter: HotPresenter

    object HOLDER {
        val INSTANCE by lazy { HotFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    init {
        DaggerHotPresenterComponent.builder()
                .build()
                .inject(this)
        hotPresenter.setHotView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hotPresenter.getWallpaperList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private var hotAdapter: HotAdapter? = null

    override fun updateHotWallpaperList(list: List<WallpaperItem>) {
        if (hotAdapter == null) {
            hotAdapter = HotAdapter(list)
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = hotAdapter
        } else {
            hotAdapter?.data = list
            hotAdapter?.notifyDataSetChanged()
        }
    }


}
