package app.mumandroidproject.model

import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.constant.Constant

/**
 * Created by CodingHome on 4/14/18.
 */
class WallpaperModel private constructor() {

    object HOLDER {
        val INSTANCE by lazy { WallpaperModel() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    fun getCategories(): List<WallpaperCategory>? {
        return null
    }

    fun getWallpaperByCategory(category: String): List<WallpaperItem>? {
        return null
    }

    fun getAllWallpapers(): List<WallpaperItem>? {
        return null
    }


}