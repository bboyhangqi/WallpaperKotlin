package app.mumandroidproject.presenter

import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.view.ColumnView

/**
 * Created by CodingHome on 4/18/18.
 */
class CategoryPresenter(var columnView: ColumnView) {

    fun getImagesByCategory(category: String) {
        //WallpaperModel.instance.getWallpaperByCategory(category)
        val data = mutableListOf<WallpaperItem>()
        data.add(WallpaperItem("https://images4.alphacoders.com/876/876898.jpg", "876898", "", 0, "", ""))
        data.add(WallpaperItem("http://www.wallfizz.com/nature/nuage/6243-couche-nuageuse-WallFizz.jpg", "6243-couche-nuageuse-WallFizz", "", 0, "", ""))
        data.add(WallpaperItem("https://i.redd.it/24y4a814zy801.jpg", "24y4a814zy801", "", 0, "", ""))
        data.add(WallpaperItem("http://wallpaperlepi.com/wp-content/uploads/2015/09/Sea-Of-Colors-Abstract-HD-Images.jpg", "Sea-Of-Colors-Abstract-HD-Images", "", 0, "", ""))
        data.add(WallpaperItem("https://i.imgur.com/eWtfMME.png", "eWtfMME", "", 0, "", ""))
        data.add(WallpaperItem("https://img00.deviantart.net/1dd4/i/2017/157/7/6/the_deer_by_kryseis_art-dbbuawk.jpg", "the_deer_by_kryseis_art-dbbuawk", "", 0, "", ""))
        data.add(WallpaperItem("http://www.eyehearttravel.com/wp-content/uploads/2012/05/39547302947455316_MHlccWEz_f.jpg", "39547302947455316_MHlccWEz_f", "", 0, "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/landscape-wallpaper-free-computer-hd37-.jpg", "landscape-wallpaper-free-computer-hd37", "", 0, "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/my-wallpaper40-.jpg", "my-wallpaper40-.jpg", "", 0, "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Mountains-1920x1080-Wallpapers38-.jpg", "Mountains-1920x1080-Wallpapers38", "", 0, "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Interesting-Star-Wars-1920x108036-.jpg", "Interesting-Star-Wars-1920x108036", "", 0, "", ""))

        columnView.setWallpapers(data)
    }

}