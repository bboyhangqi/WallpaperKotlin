package app.mumandroidproject.helper

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import java.io.FileInputStream
import java.io.IOException

/**
 * Created by CodingHome on 4/20/18.
 */
class WallpaperHelper private constructor() {

    companion object {
        fun setWallpaper(bitmap: Bitmap?, context: Context) {
            val myWallpaperManager = WallpaperManager.getInstance(context)
            try {
                myWallpaperManager.setBitmap(bitmap)
                bitmap?.recycle()
            } catch (e: IOException) {
                Toast.makeText(context, "fail to set wallpaper", Toast.LENGTH_SHORT).show()
            }
        }

        fun setWallpaperFromFile(path: String, context: Context) {
            val myWallpaperManager = WallpaperManager.getInstance(context)
            try {
                myWallpaperManager.setStream(FileInputStream(path))
            } catch (e: IOException) {
                Toast.makeText(context, "fail to set wallpaper", Toast.LENGTH_SHORT).show()
            }
        }
    }


}