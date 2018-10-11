package app.mumandroidproject.category

import android.util.Log
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.mvp.BasePresenter
import app.mumandroidproject.mvp.BaseView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

/**
 * Created by CodingHome on 4/18/18.
 */
class CategoryPresenter @Inject constructor() : BasePresenter {

    private var categoryView: CategoryView? = null

    override fun setView(baseView: BaseView) {
        this.categoryView = baseView as CategoryView
    }

    fun getCategories() {
        if (categoryView == null) return
        val categoryList = mutableListOf<WallpaperCategory>()
        categoryList.add(WallpaperCategory(R.drawable.cid_meinv, Constant.CATEGORY.REQUEST_ID_BELLE, "BELLE", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_qichce, Constant.CATEGORY.REQUEST_ID_CAR, "CAR", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_dongman, Constant.CATEGORY.REQUEST_ID_COMIC, "COMIC", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_qingxin, Constant.CATEGORY.REQUEST_ID_EMOTION, "EMOTION", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_shishang, Constant.CATEGORY.REQUEST_ID_FASHION, "FASHION", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_youxi, Constant.CATEGORY.REQUEST_ID_GAME, "GAME", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_aiqing, Constant.CATEGORY.REQUEST_ID_LOVE, "LOVE", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_junshi, Constant.CATEGORY.REQUEST_ID_MILITARY, "MILITARY", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_mengchun, Constant.CATEGORY.REQUEST_ID_PET, "PET", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_jueban, Constant.CATEGORY.REQUEST_ID_QUALITY, "QUALITY", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_fengjing, Constant.CATEGORY.REQUEST_ID_SCENERY, "SCENERY", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_yundong, Constant.CATEGORY.REQUEST_ID_SPORT, "SPORT", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_mingxing, Constant.CATEGORY.REQUEST_ID_STAR, "STAR", "", ""))
        categoryList.add(WallpaperCategory(R.drawable.cid_wenzi, Constant.CATEGORY.REQUEST_ID_WORD, "WORD", "", ""))

        WallpaperModel.instance.getCategories(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEachIndexed { index, dataSnapshot -> categoryList[index].count = dataSnapshot.value.toString() }
                categoryView?.onCategoriesReady(categoryList)
                dataSnapshot.ref.removeEventListener(this)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }

}