package app.mumandroidproject.helper

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.provider.MediaStore
import android.content.ContentValues
import android.content.ContentResolver
import android.content.ContentUris
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import app.mumandroidproject.bean.WallpaperItem
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by CodingHome on 4/19/18.
 */
class LocalHelper private constructor() {

    companion object {
        private val TAG = "LocalHelper"

        private val LOCAL_FILE_DIR = Environment.getExternalStorageDirectory().toString() + File.separator + "Wallpapers" + File.separator

        fun getLocalImages(): List<String> {
            val dir = File(LOCAL_FILE_DIR)
            Log.d(TAG, "exists ${dir.exists()}")
            Log.d(TAG, "path $LOCAL_FILE_DIR")
            if (!dir.exists()) return listOf()
            return dir.list { dir, name -> name.endsWith(".jpg") }.map { LOCAL_FILE_DIR+it }
        }

        fun storeToAlternateSd(bmp: Bitmap?, title: String) {
            if (bmp == null) return
            val sdCardDirectory = File(LOCAL_FILE_DIR)
            if (!sdCardDirectory.exists())
                sdCardDirectory.mkdir()

            val image = File(sdCardDirectory, "$title.jpg")
            val imageOut = FileOutputStream(image)
            try {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, imageOut)
                Log.d(TAG, "image ${image.absolutePath}")
            } catch (ex: Exception) {
                Log.d(TAG, "Exception ${ex}")
                ex.printStackTrace()
            } finally {
                imageOut.close()
            }

        }
    }
}