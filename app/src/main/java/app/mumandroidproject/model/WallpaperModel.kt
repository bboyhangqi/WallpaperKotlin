package app.mumandroidproject.model

import android.os.Handler
import android.util.Log
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.bean.WallpaperItem
import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import java.util.*


/**
 * Created by CodingHome on 4/14/18.
 */
class WallpaperModel () {
    lateinit var ref : DatabaseReference
    lateinit var wallpaperList: MutableList<WallpaperItem>

//    init {
//        wallpaperList = mutableListOf()
//        ref = FirebaseDatabase.getInstance().getReference("pictures")
//        ref.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                wallpaperList.clear()
//                for (postSnapshot in dataSnapshot.getChildren()) {
//                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)
//
//                    if (wallpaper != null) {
//                        wallpaperList.add(wallpaper)
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Item failed, log a message
//                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
//            }
//        })
//    }

    object HOLDER {
        val INSTANCE by lazy { WallpaperModel() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    fun getCategories(): List<WallpaperCategory>? {
        return null
    }

    fun getWallpaperByCategory(url: String): List<WallpaperItem>? {
        return null
    }

    fun getHotWallpaper(listen:ValueEventListener){
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.addValueEventListener(listen)
    }

}