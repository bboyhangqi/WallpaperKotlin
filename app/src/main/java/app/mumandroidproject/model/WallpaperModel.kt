package app.mumandroidproject.model

import app.mumandroidproject.bean.WallpaperItem

/**
 * Created by CodingHome on 4/14/18.
 */
class WallpaperModel {

    object HOLDER {
        val INSTANCE by lazy { WallpaperModel() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    fun getCatelogs(): List<String>? {
        return null
    }

    fun getWallpaperByCatelog(catelog: String): List<WallpaperItem>? {
        return null
    }

}