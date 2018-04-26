package app.mumandroidproject.model

import app.mumandroidproject.R
import android.os.Handler
import android.util.Log
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.bean.WallpaperItem
import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import java.util.*
import app.mumandroidproject.constant.Constant

/**
 * Created by CodingHome on 4/14/18.
 */
class WallpaperModel private constructor() {
    lateinit var ref: DatabaseReference
    lateinit var wallpaperList: MutableList<WallpaperItem>

    object HOLDER {
        val INSTANCE by lazy { WallpaperModel() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    fun getCategories(listen: ValueEventListener) {
        ref = FirebaseDatabase.getInstance().getReference("Category")
        ref.addValueEventListener(listen)
    }

    fun getWallpaperByCategory(category: String, listen: ValueEventListener) {
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.orderByChild("category").equalTo(category).addValueEventListener(listen)
    }

    fun getAllWallpapers(): List<WallpaperItem>? {
        return null
    }

    fun getHotWallpaper(listen: ValueEventListener) {
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.orderByChild("like").limitToLast(20).addValueEventListener(listen)
    }

    fun setLikeForWallpaper(url: String, listen: ValueEventListener) {
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.orderByChild("url").equalTo(url).addValueEventListener(listen)
    }

    fun unLikeForWallpaper(url: String, listen: ValueEventListener) {
        ref = FirebaseDatabase.getInstance().getReference("pictures")
        ref.orderByChild("url").equalTo(url).addValueEventListener(listen)
    }

}