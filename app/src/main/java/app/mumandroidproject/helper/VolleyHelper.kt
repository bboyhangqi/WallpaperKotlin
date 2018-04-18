package app.mumandroidproject.helper

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class VolleyHelper private constructor(var context: Context) {

    companion object {
        private val INSTANCE: VolleyHelper? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: VolleyHelper(context)
        }
    }

    private val requestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    val ivLoader by lazy {
        ImageLoader(requestQueue, MyImageCache())
    }


    private class MyImageCache : ImageLoader.ImageCache {

        private val cache = LruCache<String, Bitmap>(20)

        override fun getBitmap(url: String?): Bitmap? {
            return cache.get(url)
        }

        override fun putBitmap(url: String?, bitmap: Bitmap?) {
            System.out.println("putBitmap")
            cache.put(url, bitmap)
        }
    }

    fun <T> addRequest(request: Request<T>) {
        requestQueue.add(request)
    }

}