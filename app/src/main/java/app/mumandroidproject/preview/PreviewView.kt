package app.mumandroidproject.preview

/**
 * Created by CodingHome on 4/24/18.
 */
interface PreviewView{

    fun onWallpaperSet()

    fun onWallpaperSetFromLocal()

    fun onWallpaperDownloaded()

    fun onWallpaperCollected()

    fun onWallpaperUncollected()
}