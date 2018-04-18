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
import android.widget.TextView
import app.mumandroidproject.R
import app.mumandroidproject.extension.loadByGlide
import app.mumandroidproject.extension.loadImage
import app.mumandroidproject.helper.VolleyHelper
import com.android.volley.toolbox.NetworkImageView


/**
 * Created by CodingHome on 4/16/18.
 */
class ColumnAdapter(var data: List<WallpaperItem>, var categoryName: String, windowManager: WindowManager) : RecyclerView.Adapter<ColumnAdapter.ViewHolder>() {

    private val TAG = "ColumnAdapter"

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    var headItem: WallpaperItem? = null
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
            holder.headLayout.visibility = View.VISIBLE
            holder.headLayout.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.ivHeadImage.setPadding(0, 0, 0, 2)
            holder.ivHeadImage.loadByGlide(headItem?.url)
            holder.tvHeadCatelog.text = categoryName
            holder.tvHeadCount.text = data.size.toString()
            holder.iv2.visibility = View.GONE
            holder.iv1.visibility = View.GONE
        } else {
            holder.headLayout.visibility = View.GONE
            holder.iv2.visibility = View.VISIBLE
            holder.iv1.visibility = View.VISIBLE
            imageWidth = screenWidth / 2
            holder.iv1.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv1.setPadding(0, 2, 2, 2)
            holder.iv2.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv2.setPadding(2, 2, 0, 2)
            holder.iv1.loadByGlide(itemList[position][0].url)
            holder.iv2.loadByGlide(itemList[position][1].url)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv1 = itemView.findViewById<ImageView>(R.id.iv1)
        var iv2 = itemView.findViewById<ImageView>(R.id.iv2)

        /********head view********/
        var headLayout = itemView.findViewById<RelativeLayout>(R.id.rl_head)
        var ivHeadImage = itemView.findViewById<ImageView>(R.id.iv_head)
        var tvHeadCatelog = itemView.findViewById<TextView>(R.id.tv_head_catelog)
        var tvHeadCount = itemView.findViewById<TextView>(R.id.tv_head_count)

    }


    init {
        val display = windowManager.getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        headItem = data[0]
        data.filterIndexed { index, wallpaperItem -> index != 0 }
                .forEachIndexed { index, wallpaperItem -> if (index % 2 == 0) itemList.add(arrayOf(data[index], data[index + 1])) }
    }


}