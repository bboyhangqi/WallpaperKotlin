package app.mumandroidproject.presenter

import android.content.Context
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.ui.dialog.PresentationDialog
import app.mumandroidproject.view.LocalView

/**
 * Created by CodingHome on 4/23/18.
 */
class LocalPresenter(var localView: LocalView) {

    fun deleteDownloadImage(context: Context, localImageItem: LocalImageItem?) {
        LocalHelper.deleteLocalImage(localImageItem?.path)
        SharePerferenceHelper.deleteDownloadWallpaper(context, localImageItem)
        localView.onDownloadImageDeleted()
    }

    fun deleteCollectImage(context: Context, item: WallpaperItem?) {
        SharePerferenceHelper.deleteCollectWallpaper(context,item)
        localView.onCollectedItemDeleted()
    }


}