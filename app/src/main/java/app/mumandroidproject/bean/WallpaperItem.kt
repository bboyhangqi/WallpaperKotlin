package app.mumandroidproject.bean

import java.io.Serializable

/**
 * Created by CodingHome on 4/14/18.
 */
data class WallpaperItem(var url: String
                         , var name: String
                         , var desc: String
                         , var like: Int
                         , var date: String
                         , var category: String) : Serializable {
    constructor() : this("","", "", 0, "", "")

}