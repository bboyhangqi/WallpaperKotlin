package app.mumandroidproject.presenter

import android.content.Context
import android.util.Log
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.helper.WallpaperHelper
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.ui.dialog.PresentationDialog
import app.mumandroidproject.view.LocalView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
        Observable.create(ObservableOnSubscribe<Unit> { e -> e.onNext(handleRemove(item, context)) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    localView.onCollectedItemDeleted()
                })
    }

    private fun handleRemove(wallpaperItem: WallpaperItem?, context: Context) {
        Log.d("LocalPresenter","zhq.debug  handleRemove")
        SharePerferenceHelper.deleteCollectWallpaper(context, wallpaperItem)
        WallpaperModel.instance.unLikeForWallpaper(wallpaperItem?.url.toString(), object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.getChildren()) {
                    if (postSnapshot.child("url").getValue().toString().equals(wallpaperItem?.url.toString())) {
                        postSnapshot.ref.child("like").setValue(wallpaperItem?.like?.toInt()!!.minus(1))
                        break
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