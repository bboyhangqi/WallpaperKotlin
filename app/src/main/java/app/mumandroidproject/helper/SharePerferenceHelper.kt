package app.mumandroidproject.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import com.google.gson.Gson

/**
 * Created by CodingHome on 4/23/18.
 */
class SharePerferenceHelper private constructor() {

    companion object {

        fun addDownloadWallpaper(context: Context, item: LocalImageItem) {
            val array = getDownloadWallpapers(context)
            array.plus(item)
            storeDownloadWallpapers(context, array)
        }

      
        fun storeDownloadWallpapers(context: Context, list: Array<LocalImageItem>) {
            val sp = context.getSharedPreferences("download", Context.MODE_PRIVATE)
            val editor = sp.edit()
            val gsonStr = Gson().toJson(list)
            Log.d("debug", "storeDownloadWallpapers $gsonStr")
            editor.putString("local_images", gsonStr)
            editor.apply()
        }

        fun getDownloadWallpapers(context: Context): Array<LocalImageItem> {
            val sp = context.getSharedPreferences("download", Context.MODE_PRIVATE)
            val gsonStr = sp.getString("local_images", null) ?: return emptyArray()
            Log.d("debug", "getDownloadWallpapers $gsonStr")
            return Gson().fromJson(gsonStr, Array<LocalImageItem>::class.java)
        }

        fun addCollectWallpaper(context: Context, item: WallpaperItem) {
            val array = getCollectWallpapers(context)
            array.plus(item)
            storeCollectWallpapers(context, array)
        }

        
        fun storeCollectWallpapers(context: Context, list: Array<WallpaperItem>) {
            val sp = context.getSharedPreferences("collect", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("collected_images", Gson().toJson(list))
            editor.apply()
        }

        fun getCollectWallpapers(context: Context): Array<WallpaperItem> {
            val sp = context.getSharedPreferences("collect", Context.MODE_PRIVATE)
            val gsonStr = sp.getString("collected_images", null)
            return Gson().fromJson(gsonStr, Array<WallpaperItem>::class.java)
        }
    }

}