package app.mumandroidproject.hot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
                .hotViewModule(HotViewModule(this))
                .build()
                .inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hotPresenter.getWallpaperList()
    }

    override fun onStart() {
        super.onStart()
    }


    override fun updateHotWallpaperList(list: List<WallpaperItem>) {
        val hotAdapter = HotAdapter(list)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = hotAdapter
    }


}
