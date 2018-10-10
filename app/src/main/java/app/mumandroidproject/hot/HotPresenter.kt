package app.mumandroidproject.hot

import android.util.Log
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.model.WallpaperModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class HotPresenter @Inject constructor(private var hotView: HotView, private var wallpaperModel: WallpaperModel) {

    private var wallpaperList: MutableList<WallpaperItem> = mutableListOf()


    fun getWallpaperList() {

        if(!wallpaperList.isEmpty()){
            hotView.updateHotWallpaperList(wallpaperList.toList())
            return
        }

        wallpaperModel.getHotWallpaper(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError?) {
                Log.e("zhq.debug onCancelled", "loadItem:onCancelled ", databaseError?.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                Log.d("zhq.debug onDataChange","..in......")
                if (dataSnapshot == null) return
                wallpaperList.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)
                    if (wallpaper != null) {
                        wallpaperList.add(wallpaper)
                    }
                }
                hotView.updateHotWallpaperList(wallpaperList.toList())
            }
        })

    }


}