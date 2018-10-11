package app.mumandroidproject.category

import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.mvp.BaseView

/**
 * Created by CodingHome on 4/24/18.
 */
interface CategoryView : BaseView {

    fun onCategoriesReady(categories: List<WallpaperCategory>)

}