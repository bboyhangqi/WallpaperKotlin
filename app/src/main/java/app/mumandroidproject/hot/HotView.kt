package app.mumandroidproject.hot

import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.mvp.BaseView

interface HotView : BaseView {

    fun updateHotWallpaperList(list: List<WallpaperItem>)
}