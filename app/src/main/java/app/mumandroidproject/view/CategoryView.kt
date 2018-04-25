package app.mumandroidproject.view

import app.mumandroidproject.bean.WallpaperCategory

/**
 * Created by CodingHome on 4/24/18.
 */
interface CategoryView {

    fun onCategoriesReady(categories: List<WallpaperCategory>)

}