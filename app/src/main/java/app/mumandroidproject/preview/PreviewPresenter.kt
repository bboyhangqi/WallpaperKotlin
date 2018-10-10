package app.mumandroidproject.preview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.helper.WallpaperHelper
import app.mumandroidproject.model.WallpaperModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by CodingHome on 4/24/18.
 */
@SuppressLint("CheckResult")
class PreviewPresenter(var previewView: PreviewView) {

    fun setWallpaperFromLocal(path: String, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaperFromFile(path, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperSetFromLocal()
                }
    }

    fun setWallpaperFromLocal(bitmap: Bitmap?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaper(bitmap, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperSetFromLocal()
                }
    }

    fun setWallpaper(bitmap: Bitmap?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaper(bitmap, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperSet()
                }
    }


    fun downloadWallpaper(bitmap: Bitmap?, name: String, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e ->
            e.onNext(SharePerferenceHelper.addDownloadWallpaper(context, LocalImageItem(LocalHelper.storeToAlternateSd(bitmap, name))))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperDownloaded()
                }

    }

    fun collectWallpaper(wallpaperItem: WallpaperItem?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e ->
            e.onNext(handleCollect(wallpaperItem, context))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperCollected()
                }
    }

    fun unCollectWallpaper(wallpaperItem: WallpaperItem?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e ->
            e.onNext(handleRemove(wallpaperItem!!, context))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    previewView.onWallpaperUncollected()
                }
    }

    fun isWallpaperCollected(wallpaperItem: WallpaperItem?, context: Context): Boolean {
        return SharePerferenceHelper.isWallpaperCollected(context, wallpaperItem!!)
    }

    private fun handleCollect(wallpaperItem: WallpaperItem?, context: Context) {
        SharePerferenceHelper.addCollectWallpaper(context, wallpaperItem!!)
        WallpaperModel.instance.setLikeForWallpaper(wallpaperItem?.url.toString(), object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.getChildren()) {
                    if (postSnapshot.child("url").getValue().toString().equals(wallpaperItem?.url.toString())) {
                        postSnapshot.ref.child("like").setValue(wallpaperItem?.like?.toInt()!!.plus(1))
                    }
                }
                dataSnapshot.ref.removeEventListener(this)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }

    private fun handleRemove(wallpaperItem: WallpaperItem?, context: Context) {
        Log.d("PreviewPresenter", "zhq.debug  handleRemove")
        SharePerferenceHelper.deleteCollectWallpaper(context, wallpaperItem)
        WallpaperModel.instance.unLikeForWallpaper(wallpaperItem?.url.toString(), object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.getChildren()) {
                    if (postSnapshot.child("url").getValue().toString().equals(wallpaperItem?.url.toString())) {
                        postSnapshot.ref.child("like").setValue(wallpaperItem?.like?.toInt()!!.minus(1))
                    }
                }
                dataSnapshot.ref.removeEventListener(this)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }
}