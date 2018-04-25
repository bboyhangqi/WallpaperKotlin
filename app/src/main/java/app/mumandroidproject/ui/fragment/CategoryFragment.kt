package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.presenter.CategoryPresenter
import app.mumandroidproject.ui.adpter.CategoryAdapter
import app.mumandroidproject.view.CategoryView
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() , CategoryView {

    private val TAG = "CategoryFragment"

    private var categoryPresenter = CategoryPresenter(this)

    object HOLDER {
        val INSTANCE by lazy { CategoryFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getCategories()
    }

    private fun getCategories(){
        categoryPresenter.getCategories()
    }

    override fun onCategoriesReady(categories: List<WallpaperCategory>) {
        val categoryAdapter = CategoryAdapter(categories)
        rv.layoutManager = LinearLayoutManager(CategoryFragment.instance.context)
        rv.adapter = categoryAdapter
    }
}
