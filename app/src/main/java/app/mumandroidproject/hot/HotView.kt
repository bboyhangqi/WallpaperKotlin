package app.mumandroidproject.hot

import app.mumandroidproject.bean.WallpaperItem

interface HotView {

    fun updateHotWallpaperList(list: List<WallpaperItem>)
}