package app.mumandroidproject.ui.activity

import android.graphics.Bitmap
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import app.mumandroidproject.R
import app.mumandroidproject.extension.loadByGlide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_preview.*
import android.widget.Toast
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.NotificationHelper
import app.mumandroidproject.helper.WallpaperHelper
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PreviewActivity : AppCompatActivity(), RequestListener<Bitmap> {

    private val TAG = "PreviewActivity"
    private var bitmap: Bitmap? = null
    private var wallpaperItem: WallpaperItem? = null
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        loadImage()
    }

    fun loadImage(){
        var flag = intent.getStringExtra("flag")
        when (flag) {
            "local" -> {
                var path = intent.getStringExtra("path")
                iv.loadByGlide(wallpaperItem?.url, this)
            }
            "online" -> {
                wallpaperItem = intent.getSerializableExtra("wallpaperItem") as WallpaperItem
                iv.loadByGlide(wallpaperItem?.url, this)
            }
        }
    }


    fun back(view: View) {
        finish()
    }

    fun downLoad(view: View) {
        if (bitmap == null || bitmap!!.isRecycled) {
            Toast.makeText(this, "fail to download wallpaper", Toast.LENGTH_SHORT).show()
        }
        LocalHelper.storeToAlternateSd(bitmap, wallpaperItem!!.name)
        NotificationHelper.sendMsg(this, "Notice", "wallpaper download success", R.drawable.ok)
    }


    fun setWallpaper(view: View) {
        if (bitmap == null) {
            Toast.makeText(this, "fail to set wallpaper", Toast.LENGTH_SHORT).show()
            return
        }
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaper(bitmap, this)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    LocalHelper.storeToAlternateSd(bitmap, wallpaperItem!!.name)
                    NotificationHelper.sendMsg(this, "Notice", "set wallpaper success", R.drawable.rotatebigbk)
                    Toast.makeText(this, "set wallpaper success", Toast.LENGTH_SHORT).show()
                })
    }


    fun collect(view: View) {
        //LocalHelper.getLocalImages()?.forEach { Log.d(TAG, "path: $it") }
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
        Log.d(TAG, "onLoadFailed")
        return false
    }

    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        Log.d(TAG, "onResourceReady")
        bitmap = resource
        return false
    }
}
