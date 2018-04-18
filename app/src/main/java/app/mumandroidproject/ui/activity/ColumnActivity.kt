package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.ui.adpter.ColumnAdapter
import kotlinx.android.synthetic.main.activity_column.*

class ColumnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_column)
        val category = intent.getStringExtra("category")
        rv.layoutManager = LinearLayoutManager(this)
        val data = mutableListOf<WallpaperItem>()
        data.add(WallpaperItem("https://images4.alphacoders.com/876/876898.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.wallfizz.com/nature/nuage/6243-couche-nuageuse-WallFizz.jpg", "", "", "", ""))
        data.add(WallpaperItem("https://i.redd.it/24y4a814zy801.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://wallpaperlepi.com/wp-content/uploads/2015/09/Sea-Of-Colors-Abstract-HD-Images.jpg", "", "", "", ""))
        data.add(WallpaperItem("https://i.imgur.com/eWtfMME.png", "", "", "", ""))
        data.add(WallpaperItem("https://img00.deviantart.net/1dd4/i/2017/157/7/6/the_deer_by_kryseis_art-dbbuawk.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.eyehearttravel.com/wp-content/uploads/2012/05/39547302947455316_MHlccWEz_f.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/landscape-wallpaper-free-computer-hd37-.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/my-wallpaper40-.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Mountains-1920x1080-Wallpapers38-.jpg", "", "", "", ""))
        data.add(WallpaperItem("http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Interesting-Star-Wars-1920x108036-.jpg", "", "", "", ""))

        val columnAdapter = ColumnAdapter(data, category, windowManager)
        rv.adapter = columnAdapter
    }

    //https://images4.alphacoders.com/876/876898.jpg
    //http://www.wallfizz.com/nature/nuage/6243-couche-nuageuse-WallFizz.jpg
    //https://i.redd.it/24y4a814zy801.jpg
    //http://wallpaperlepi.com/wp-content/uploads/2015/09/Sea-Of-Colors-Abstract-HD-Images.jpg
    //https://i.imgur.com/eWtfMME.png
    //https://img00.deviantart.net/1dd4/i/2017/157/7/6/the_deer_by_kryseis_art-dbbuawk.jpg
    //http://www.eyehearttravel.com/wp-content/uploads/2012/05/39547302947455316_MHlccWEz_f.jpg
    //http://www.backgroundimageshd.com/wp-content/uploads/2017/12/landscape-wallpaper-free-computer-hd37-.jpg
    //http://www.backgroundimageshd.com/wp-content/uploads/2017/12/my-wallpaper40-.jpg
    //http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Mountains-1920x1080-Wallpapers38-.jpg
    //http://www.backgroundimageshd.com/wp-content/uploads/2017/12/Interesting-Star-Wars-1920x108036-.jpg

    override fun onStart() {
        super.onStart()
    }


}
