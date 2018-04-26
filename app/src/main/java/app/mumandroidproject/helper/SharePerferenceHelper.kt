package app.mumandroidproject.helper

import android.content.Context
import android.util.Log
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import com.google.gson.Gson

/**
 *
 *
 * Created by CodingHome on 4/23/18.
 *
 */
class SharePerferenceHelper private constructor() {

    companion object {

        fun addDownloadWallpaper(context: Context, item: LocalImageItem) {
            val array = getDownloadWallpapers(context)
            val list = array.toMutableList()
            if (list.contains(item)) {
                return
            }
            list.add(item)
            storeDownloadWallpapers(context, list)
        }


        private fun storeDownloadWallpapers(context: Context, list: List<LocalImageItem>) {
            val sp = context.getSharedPreferences("download", Context.MODE_PRIVATE)
            val editor = sp.edit()
            val gsonStr = Gson().toJson(list)
            editor.putString("local_images", gsonStr)
            editor.apply()
        }

        fun getDownloadWallpapers(context: Context): Array<LocalImageItem> {
            val sp = context?.getSharedPreferences("download", Context.MODE_PRIVATE)
            val gsonStr = sp?.getString("local_images", null) ?: return emptyArray()
            return Gson().fromJson(gsonStr, Array<LocalImageItem>::class.java)
        }


        fun deleteDownloadWallpaper(context: Context, item: LocalImageItem?) {
            val arrays = getDownloadWallpapers(context)
            val list = arrays.toMutableList()
            if (list.contains(item)) {
                list.remove(item)
            }
            storeDownloadWallpapers(context, list)
        }


        fun addCollectWallpaper(context: Context, item: WallpaperItem) {
            val array = getCollectWallpapers(context)
            val list = array.toMutableList()
            if (list.filter { it.url == item.url }.count() > 0) {
                return
            }
            list.add(item)
            storeCollectWallpapers(context, list)
        }

        fun isWallpaperCollected(context: Context, item: WallpaperItem): Boolean {
            val array = getCollectWallpapers(context)
            val list = array.toMutableList()
            return list.filter { it.url == item.url }.count() > 0
        }


        private fun storeCollectWallpapers(context: Context, list: List<WallpaperItem>) {
            val sp = context.getSharedPreferences("collect", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("collected_images", Gson().toJson(list))
            editor.apply()
        }

        fun getCollectWallpapers(context: Context): Array<WallpaperItem> {
            val sp = context?.getSharedPreferences("collect", Context.MODE_PRIVATE)
            val gsonStr = sp?.getString("collected_images", null) ?: return emptyArray()
            Log.d("debug", "zhq.......gsonStr: $gsonStr")
            return Gson().fromJson(gsonStr, Array<WallpaperItem>::class.java)
        }

        fun deleteCollectWallpaper(context: Context, wallpaperItem: WallpaperItem?) {
            Log.d("debug","zhq.debug deleteCollectWallpaper url${wallpaperItem?.url}")
            val arrays = getCollectWallpapers(context)
            val list = arrays.toMutableList()
            for (item in list){
                if(item.url == wallpaperItem?.url){
                    list.remove(item)
                }
            }
            storeCollectWallpapers(context, list)
        }
    }

}