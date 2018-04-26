package app.mumandroidproject.constant

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory

/**
 * Created by CodingHome on 4/16/18.
 */
class Constant {

    object CATEGORY {
        val categoryList = mutableListOf<WallpaperCategory>()
        val REQUEST_ID_QUALITY = "quality"
        val REQUEST_ID_SCENERY = "scenery"
        val REQUEST_ID_BELLE = "belle"
        val REQUEST_ID_WORD = "word"
        val REQUEST_ID_LOVE = "love"
        val REQUEST_ID_COMIC = "comic"
        val REQUEST_ID_EMOTION = "emotion"
        val REQUEST_ID_STAR = "star"
        val REQUEST_ID_PET = "pet"
        val REQUEST_ID_FASHION = "fashion"
        val REQUEST_ID_GAME = "game"
        val REQUEST_ID_SPORT = "sport"
        val REQUEST_ID_CAR = "car"
        val REQUEST_ID_MILITARY = "military"

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

    object PREVIEW_TYPE {
        val LOCAL = 1
        val ONLINE = 2
        val COLLECT = 3
    }

    object BROADCAST_ACTION {
        val ACTION_IMAGE_DOWNLOADED = "com.mum.wallpaper.action.ACTION_IMAGE_DOWNLOADED"
        val ACTION_IMAGE_COLLECTED = "com.mum.wallpaper.action.ACTION_IMAGE_COLLECTED"
        val ACTION_IMAGE_UNCOLLECTED = "com.mum.wallpaper.action.ACTION_IMAGE_UNCOLLECTED"
    }

    object HOME_PAGE_ID {
        val PAGE_HOT = 1
        val PAGE_CATEGORY = 2
        val PAGE_LOCAL = 3
    }

    object PREVIEW_PAGE_STATE{
        val STATE_IDAL = 1
        val STATE_DOWNLOADING = 2
        val STATE_SETTING = 3
        val STATE_COLLECTING = 4
    }


}