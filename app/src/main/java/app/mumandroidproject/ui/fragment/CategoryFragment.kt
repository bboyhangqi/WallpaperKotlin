package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.ui.adpter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {

    private val TAG = "CategoryFragment"

    object HOLDER {
        val INSTANCE by lazy { CategoryFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG,"..zhq.debug......")
        return inflater!!.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryAdapter = CategoryAdapter(Constant.CATEGORY.categoryList)
        rv.layoutManager = LinearLayoutManager(this.context)
        rv.adapter = categoryAdapter
    }

    override fun onStart() {
        super.onStart()
    }


}
