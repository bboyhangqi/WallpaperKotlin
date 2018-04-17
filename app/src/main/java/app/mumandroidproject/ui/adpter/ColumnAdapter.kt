package app.mumandroidproject.ui.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import app.mumandroidproject.bean.WallpaperItem
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import app.mumandroidproject.R
import app.mumandroidproject.extension.loadByGlide


/**
 * Created by CodingHome on 4/16/18.
 */
class ColumnAdapter(var data: List<WallpaperItem>, windowManager: WindowManager) : RecyclerView.Adapter<ColumnAdapter.ViewHolder>() {

    private val TAG = "ColumnAdapter"

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    var titleItem: WallpaperItem? = null
    var itemList = mutableListOf<Array<WallpaperItem>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return (data.size - 1) / 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageWidth: Int
        if (position == 0) {
            imageWidth = screenWidth
            holder.iv1.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv1.setPadding(0, 0, 0, 2)
            holder.iv2.visibility = View.GONE
            holder.iv1.loadByGlide(titleItem?.url)
            Log.d(TAG, "..zhq.debug..title..${titleItem?.url}..")
        } else {
            imageWidth = screenWidth / 2
            holder.iv1.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv1.setPadding(0, 2, 2, 2)
            holder.iv2.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv2.setPadding(2, 2, 0, 2)
            holder.iv2.visibility = View.VISIBLE
            holder.iv1.loadByGlide(itemList[position][0].url)
            holder.iv2.loadByGlide(itemList[position][1].url)
            Log.d(TAG, "..zhq.debug....${itemList[position][0].url}..")
            Log.d(TAG, "..zhq.debug....${itemList[position][1].url}..")
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv1 = itemView.findViewById<ImageView>(R.id.iv1)
        var iv2 = itemView.findViewById<ImageView>(R.id.iv2)
    }


    init {
        val display = windowManager.getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        titleItem = data[0]
        data.filterIndexed { index, wallpaperItem -> index != 0 }
                .forEachIndexed { index, wallpaperItem -> if (index % 2 == 0) itemList.add(arrayOf(data[index], data[index + 1])) }

    }


}