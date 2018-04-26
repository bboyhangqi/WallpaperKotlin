package app.mumandroidproject.presenter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import app.mumandroidproject.R
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.NotificationHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.helper.WallpaperHelper
import app.mumandroidproject.view.PreviewView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by CodingHome on 4/24/18.
 */
class PreviewPresenter(var previewView: PreviewView) {

    fun setWallpaperFromLocal(path: String, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaperFromFile(path, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    previewView.onWallpaperSetFromLocal()
                })
    }

    fun setWallpaperFromLocal(bitmap: Bitmap?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaper(bitmap, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    previewView.onWallpaperSetFromLocal()
                })
    }

    fun setWallpaper(bitmap: Bitmap?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(WallpaperHelper.setWallpaper(bitmap, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    previewView.onWallpaperSet()
                })
    }

    fun downloadWallpaper(bitmap: Bitmap?, name: String, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e ->
            e.onNext(SharePerferenceHelper.addDownloadWallpaper(context, LocalImageItem(LocalHelper.storeToAlternateSd(bitmap, name))))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    previewView.onWallpaperDownloaded()
                })

    }

    fun collectWallpaper(wallpaperItem: WallpaperItem?, context: Context) {
        Observable.create(ObservableOnSubscribe<Unit> { e ->
            e.onNext(SharePerferenceHelper.addCollectWallpaper(context, wallpaperItem!!))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    previewView.onWallpaperCollected()
                })
    }


}