package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.model.WallpaperModel
import app.mumandroidproject.ui.adpter.HotAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_category.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment() {
    var wallpaperList: MutableList<WallpaperItem> = mutableListOf()

    object HOLDER {
        val INSTANCE by lazy { HotFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        WallpaperModel.instance.getHotWallpaper(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               // wallpaperList.clear()
                for (postSnapshot in dataSnapshot.getChildren()) {
                    val wallpaper = postSnapshot.getValue(WallpaperItem::class.java)
                    if (wallpaper != null) {
                        wallpaperList.add(wallpaper)
                    }
                }
                if (rv != null) {
                    Collections.reverse(wallpaperList)
                    val hotAdapter = HotAdapter(wallpaperList)
                    rv.layoutManager = LinearLayoutManager(HotFragment.instance.context)
                    rv.adapter = hotAdapter
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
            }
        })
    }


}
