package app.mumandroidproject.bean

import java.io.Serializable

/**
 * Created by CodingHome on 4/14/18.
 */
data class WallpaperItem(var url: String
                         , var name: String
                         , var dec: String
                         , var like: String
                         , var date: String) {
    constructor() : this("","","","",""){

    }
}