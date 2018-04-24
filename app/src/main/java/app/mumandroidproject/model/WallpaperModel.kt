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

    fun getHotWallpaper(listen:ValueEventListener){
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.orderByChild("like").limitToLast(20).addValueEventListener(listen)
    }

}