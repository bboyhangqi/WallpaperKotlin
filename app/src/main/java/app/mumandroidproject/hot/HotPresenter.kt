package app.mumandroidproject.hot

import android.util.Log
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.mvp.BasePresenter
import app.mumandroidproject.mvp.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HotPresenter constructor(private var wallpaperModel: WallpaperModel) : BasePresenter {

    private var hotView: HotView? = null

    private var wallpaperList: MutableList<WallpaperItem> = mutableListOf()


    companion object {
        private var instance: HotPresenter? = null

        fun getInstance(wallpaperModel: WallpaperModel): HotPresenter {
            if (instance == null) {
                instance = HotPresenter(wallpaperModel)
            }
            return instance!!
        }
    }

    override fun setView(baseView: BaseView) {
        this.hotView = baseView as HotView
    }


    fun getWallpaperList() {
        if (hotView == null) return

        if (!wallpaperList.isEmpty()) {
            Log.d("zhq.debug", "..use old data......")
            hotView?.updateHotWallpaperList(wallpaperList.toList())
            return
        }

        wallpaperModel.getHotWallpaper(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError?) {
                Log.e("zhq.debug onCancelled", "loadItem:onCancelled ", databaseError?.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Log.d("zhq.debug onDataChange", "..in......")
                if (dataSnapshot == null) return
                wallpaperList.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)
                    if (wallpaper != null) {
                        wallpaperList.add(wallpaper)
                    }
                }
                hotView?.updateHotWallpaperList(wallpaperList.toList())
            }
        })

    }


}