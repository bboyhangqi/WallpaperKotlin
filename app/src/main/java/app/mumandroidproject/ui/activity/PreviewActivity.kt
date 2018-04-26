package app.mumandroidproject.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.extension.loadByGlideFromLocal
import app.mumandroidproject.helper.NotificationHelper
import app.mumandroidproject.presenter.PreviewPresenter
import app.mumandroidproject.view.PreviewView
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.activity_wellcome.*


class PreviewActivity : AppCompatActivity(), RequestListener<Bitmap>, PreviewView {

    private val TAG = "PreviewActivity"
    private var bitmap: Bitmap? = null
    private var wallpaperItem: WallpaperItem? = null
    private var previewType = Constant.PREVIEW_TYPE.ONLINE
    private var previewIsShown = false
    private var previewPresenter = PreviewPresenter(this)
    private var state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL

    private var isCollected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        var flag = intent.getStringExtra("flag")
        when (flag) {
            "local" -> previewType = Constant.PREVIEW_TYPE.LOCAL
            "online" -> previewType = Constant.PREVIEW_TYPE.ONLINE
            "collect" ->previewType = Constant.PREVIEW_TYPE.COLLECT
        }
        updateUI()
    }

    fun updateUI() {
        when (previewType) {
            Constant.PREVIEW_TYPE.LOCAL -> {
                iv.loadByGlideFromLocal(intent.getStringExtra("path"))
                btn_collect.visibility = View.GONE
                btn_down.visibility = View.GONE
            }
            Constant.PREVIEW_TYPE.ONLINE -> {
                wallpaperItem = intent.getSerializableExtra("wallpaperItem") as WallpaperItem
                iv.loadByGlide(wallpaperItem?.url, this)
                isCollected = previewPresenter.isWallpaperCollected(wallpaperItem, this)
                updateCollectedIcon()
            }
            Constant.PREVIEW_TYPE.COLLECT -> {
                wallpaperItem = intent.getSerializableExtra("wallpaperItem") as WallpaperItem
                iv.loadByGlide(wallpaperItem?.url, this)
                isCollected = previewPresenter.isWallpaperCollected(wallpaperItem, this)
                updateCollectedIcon()
                btn_collect.visibility = View.GONE
                btn_down.visibility = View.GONE
            }
        }
    }

    private fun updateCollectedIcon() {
        if (isCollected)
            btn_collect.setBackgroundResource(R.drawable.collect_btn_p)
        else
            btn_collect.setBackgroundResource(R.drawable.collect_btn)
    }


    fun showPreview(view: View) {
        if (!previewIsShown) {
            iv_preview.visibility = View.VISIBLE
            val bgScaleSet = AnimatorSet()
            val bgAnimatorX = ObjectAnimator.ofFloat(iv_preview, "scaleX", 1.3f, 1.0f)
            val bgAnimatorY = ObjectAnimator.ofFloat(iv_preview, "scaleY", 1.3f, 1.0f)
            bgScaleSet.duration = 1000
            bgScaleSet.play(bgAnimatorX).with(bgAnimatorY)
            bgScaleSet.start()
            previewIsShown = true
        } else {
            val bgScaleSet = AnimatorSet()
            val bgAnimatorX = ObjectAnimator.ofFloat(iv_preview, "scaleX", 1.0f, 1.5f)
            val bgAnimatorY = ObjectAnimator.ofFloat(iv_preview, "scaleY", 1.0f, 1.5f)
            bgScaleSet.duration = 1000
            bgScaleSet.play(bgAnimatorX).with(bgAnimatorY)
            bgScaleSet.start()
            Handler().postDelayed({
                iv_preview.visibility = View.INVISIBLE
                previewIsShown = false
            }, 200)
        }

    }

    override fun onWallpaperSet() {
        sendBroadcast(Intent(Constant.BROADCAST_ACTION.ACTION_IMAGE_DOWNLOADED))
        NotificationHelper.sendMsg(this, "Notice", "set wallpaper success", R.drawable.rotatebigbk)
        Toast.makeText(this, "set wallpaper success", Toast.LENGTH_SHORT).show()
        previewPresenter.downloadWallpaper(bitmap, wallpaperItem!!.name, this)
        state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL
    }

    override fun onWallpaperSetFromLocal() {
        sendBroadcast(Intent(Constant.BROADCAST_ACTION.ACTION_IMAGE_DOWNLOADED))
        NotificationHelper.sendMsg(this, "Notice", "set wallpaper success", R.drawable.rotatebigbk)
        Toast.makeText(this, "set wallpaper success", Toast.LENGTH_SHORT).show()
        state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL
    }

    override fun onWallpaperDownloaded() {
        sendBroadcast(Intent(Constant.BROADCAST_ACTION.ACTION_IMAGE_DOWNLOADED))
        NotificationHelper.sendMsg(this, "Notice", "wallpaper download success", R.drawable.ok)
        state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL
    }

    override fun onWallpaperCollected() {
        sendBroadcast(Intent(Constant.BROADCAST_ACTION.ACTION_IMAGE_COLLECTED))
        state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL
    }

    override fun onWallpaperUncollected() {
        Log.d(TAG,"zhq.debug onWallpaperUncollected")
        sendBroadcast(Intent(Constant.BROADCAST_ACTION.ACTION_IMAGE_UNCOLLECTED))
        state = Constant.PREVIEW_PAGE_STATE.STATE_IDAL
    }

    fun downLoad(view: View) {
        if (bitmap == null || bitmap!!.isRecycled) {
            Toast.makeText(this, "fail to download wallpaper", Toast.LENGTH_SHORT).show()
            return
        }
        if (state == Constant.PREVIEW_PAGE_STATE.STATE_DOWNLOADING) {
            Toast.makeText(this, "wallpaper is downloading", Toast.LENGTH_SHORT).show()
            return
        }
        state = Constant.PREVIEW_PAGE_STATE.STATE_DOWNLOADING
        previewPresenter.downloadWallpaper(bitmap!!, wallpaperItem!!.name, this)
    }

    fun setWallpaper(view: View) {
        Log.d(TAG, "zhq.debug setWallpaper previewType: $previewType")
        if (state == Constant.PREVIEW_PAGE_STATE.STATE_SETTING) {
            Toast.makeText(this, "wallpaper is setting", Toast.LENGTH_SHORT).show()
            return
        }
        state = Constant.PREVIEW_PAGE_STATE.STATE_SETTING
        when (previewType) {
            Constant.PREVIEW_TYPE.LOCAL -> previewPresenter.setWallpaperFromLocal(iv.getBitmapFromArea(), this)
            Constant.PREVIEW_TYPE.ONLINE -> previewPresenter.setWallpaper(iv.getBitmapFromArea(), this)
        }
    }

    fun collect(view: View) {
        if (state == Constant.PREVIEW_PAGE_STATE.STATE_COLLECTING) {
            Toast.makeText(this, "wallpaper is collecting", Toast.LENGTH_SHORT).show()
            return
        }
        state = Constant.PREVIEW_PAGE_STATE.STATE_COLLECTING
        if (isCollected)
            previewPresenter.unCollectWallpaper(wallpaperItem, this)
        else
            previewPresenter.collectWallpaper(wallpaperItem, this)
        isCollected = !isCollected
        updateCollectedIcon()
    }

    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
        Log.d(TAG, "onLoadFailed")
        return false
    }

    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        Log.d(TAG, "onResourceReady")
        bitmap = resource
        iv.setImageBitmap(bitmap)
        return true
    }

    fun back(view: View) {
        finish()
    }
}
