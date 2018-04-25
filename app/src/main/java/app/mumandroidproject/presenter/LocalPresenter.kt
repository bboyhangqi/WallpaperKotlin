package app.mumandroidproject.presenter

import android.content.Context
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.helper.WallpaperHelper
import app.mumandroidproject.ui.dialog.PresentationDialog
import app.mumandroidproject.view.LocalView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by CodingHome on 4/23/18.
 */
class LocalPresenter(var localView: LocalView) {

    fun deleteDownloadImage(context: Context, localImageItem: LocalImageItem?) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(handleDeleteDownloadImage(context, localImageItem)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    localView.onDownloadImageDeleted()
                })
    }

    private fun handleDeleteDownloadImage(context: Context, localImageItem: LocalImageItem?) {
        LocalHelper.deleteLocalImage(localImageItem?.path)
        SharePerferenceHelper.deleteDownloadWallpaper(context, localImageItem)
    }

    fun deleteCollectImage(context: Context, item: WallpaperItem?) {
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(SharePerferenceHelper.deleteCollectWallpaper(context, item)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    localView.onCollectedItemDeleted()
                })
    }


}