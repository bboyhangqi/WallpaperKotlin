package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.presenter.CategoryPresenter
import app.mumandroidproject.presenter.ColumPresenter
import app.mumandroidproject.ui.adpter.ColumnAdapter
import app.mumandroidproject.view.ColumnView
import kotlinx.android.synthetic.main.activity_column.*

class ColumnActivity : AppCompatActivity(), ColumnView {

    private val category by lazy { intent.getStringExtra("category") }

    private val columPresenter by lazy { ColumPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_column)
        rv.layoutManager = LinearLayoutManager(this)
        columPresenter.getImagesByCategory(category)
    }

    override fun setWallpapers(wallpapers: List<WallpaperItem>) {
        if (wallpapers.isEmpty()) return
        val columnAdapter = ColumnAdapter(wallpapers, category, windowManager)
        rv.adapter = columnAdapter
    }

}
