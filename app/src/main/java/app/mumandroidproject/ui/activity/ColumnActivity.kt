package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.presenter.CategoryPresenter
import app.mumandroidproject.ui.adpter.ColumnAdapter
import app.mumandroidproject.view.ColumnView
import kotlinx.android.synthetic.main.activity_column.*

class ColumnActivity : AppCompatActivity(), ColumnView {

    private val categoryTitle by lazy { intent.getStringExtra("category") }
    private val categoryRequestId by lazy { intent.getIntExtra("requestId", -1) }

    private val categoryPresenter by lazy { CategoryPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_column)
        rv.layoutManager = LinearLayoutManager(this)
        categoryPresenter.getImagesByCategory(categoryRequestId)
    }

    override fun setWallpapers(wallpapers: List<WallpaperItem>) {
        val columnAdapter = ColumnAdapter(wallpapers, categoryTitle, windowManager)
        rv.adapter = columnAdapter
    }

}
