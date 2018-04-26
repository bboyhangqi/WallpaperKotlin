package app.mumandroidproject.presenter

import android.util.Log
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.view.ColumnView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

/**
 * Created by CodingHome on 4/24/18.
 */
class ColumPresenter(var columnView: ColumnView) {

    fun getImagesByCategory(category: String) {
        val data = mutableListOf<WallpaperItem>()
        WallpaperModel.instance.getWallpaperByCategory(category, object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                data.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)
                    if (wallpaper != null) {
                        data.add(wallpaper)
                    }
                }
                columnView.setWallpapers(data)
                dataSnapshot.ref.removeEventListener(this)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }
}