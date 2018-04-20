package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.ui.adpter.CategoryAdapter
import app.mumandroidproject.ui.adpter.HotAdapter
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment() {

    object HOLDER {
        val INSTANCE by lazy { HotFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val hotAdapter = HotAdapter(Constant.CATEGORY.categoryList)
        rv.layoutManager = LinearLayoutManager(this.context)
        rv.adapter = hotAdapter
    }

}
