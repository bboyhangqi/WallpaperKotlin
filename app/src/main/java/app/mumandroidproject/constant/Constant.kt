package app.mumandroidproject.constant

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory

/**
 * Created by CodingHome on 4/16/18.
 */
class Constant {

    object CATEGORY {
        val categoryList = mutableListOf<WallpaperCategory>()
        val REQUEST_ID_QUALITY = 1
        val REQUEST_ID_SCENERY = 2
        val REQUEST_ID_BELLE = 3
        val REQUEST_ID_WORD = 4
        val REQUEST_ID_LOVE = 5
        val REQUEST_ID_COMIC = 6
        val REQUEST_ID_EMOTION = 7
        val REQUEST_ID_STAR = 8
        val REQUEST_ID_PET = 9
        val REQUEST_ID_FASHION = 10
        val REQUEST_ID_GAME = 11
        val REQUEST_ID_SPORT = 12
        val REQUEST_ID_CAR = 13
        val REQUEST_ID_MILITARY = 14

        init {
            categoryList.add(WallpaperCategory(R.drawable.cid_jueban, REQUEST_ID_QUALITY, "QUALITY", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_fengjing, REQUEST_ID_SCENERY, "SCENERY", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_meinv, REQUEST_ID_BELLE, "BELLE", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_wenzi, REQUEST_ID_WORD, "WORD", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_aiqing, REQUEST_ID_LOVE, "LOVE", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_dongman, REQUEST_ID_COMIC, "COMIC", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_qingxin, REQUEST_ID_EMOTION, "EMOTION", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_mingxing, REQUEST_ID_STAR, "STAR", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_mengchun, REQUEST_ID_PET, "PET", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_shishang, REQUEST_ID_FASHION, "FASHION", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_youxi, REQUEST_ID_GAME, "GAME", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_yundong, REQUEST_ID_SPORT, "SPORT", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_qichce, REQUEST_ID_CAR, "CAR", "", "100"))
            categoryList.add(WallpaperCategory(R.drawable.cid_junshi, REQUEST_ID_MILITARY, "MILITARY", "", "100"))
        }
    }


}