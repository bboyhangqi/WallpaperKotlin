package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.ui.adpter.CategoryAdapter
import app.mumandroidproject.ui.adpter.HotAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_category.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class CategoryFragment : Fragment() {
    val categoryCount: MutableList<Long> = mutableListOf()
    val categoryList = mutableListOf<WallpaperCategory>()

    private val TAG = "CategoryFragment"

    object HOLDER {
        val INSTANCE by lazy { CategoryFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.getCategories()
    }

    fun getCategories(){
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
                for (postSnapshot in dataSnapshot.getChildren()) {
                    categoryCount.add(postSnapshot.getValue() as Long)
                }

                for (i in 0..categoryList.count()-1) {
                    categoryList.get(i).count = categoryCount.get(i).toString()
                }

                val categoryAdapter = CategoryAdapter(categoryList)
                rv.layoutManager = LinearLayoutManager(CategoryFragment.instance.context)
                rv.adapter = categoryAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Item failed, log a message
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }
}
