package app.mumandroidproject.ui.adpter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import app.mumandroidproject.bean.WallpaperItem
import android.graphics.Point
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import app.mumandroidproject.R
import app.mumandroidproject.extension.loadByGlide
import app.mumandroidproject.ui.activity.PreviewActivity


/**
 * Created by CodingHome on 4/16/18.
 */
class ColumnAdapter(var data: List<WallpaperItem>, var categoryName: String, windowManager: WindowManager) : RecyclerView.Adapter<ColumnAdapter.ViewHolder>() {

    private val TAG = "ColumnAdapter"

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    private var headItem: WallpaperItem? = null
    private var itemList = mutableListOf<Array<WallpaperItem>>()

    init {
        val display = windowManager.getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        headItem = data[0]
        for ((index, value) in data.withIndex()) {
            if (index == 0) continue
            if (index + 1 == data.size) {
                if (index % 2 != 0) itemList.add(arrayOf(data[index]))
            } else {
                if (index % 2 != 0) itemList.add(arrayOf(data[index], data[index + 1]))
            }
        }

        Log.d(TAG, "size: ${itemList.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size + 1
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
            holder.itemHead = headItem
        } else {
            holder.headLayout.visibility = View.GONE
            holder.iv1.visibility = View.VISIBLE
            imageWidth = screenWidth / 2
            holder.iv1.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv1.setPadding(0, 2, 2, 2)
            holder.iv1.loadByGlide(itemList[position - 1][0].url)
            holder.itemForIv1 = itemList[position - 1][0]
            if (itemList[position - 1].size == 2) {
                holder.iv2.visibility = View.VISIBLE
                holder.iv2.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
                holder.iv2.setPadding(2, 2, 0, 2)
                holder.iv2.loadByGlide(itemList[position - 1][1].url)
                holder.itemForIv2 = itemList[position - 1][1]
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var itemHead: WallpaperItem? = null
        var itemForIv1: WallpaperItem? = null
        var itemForIv2: WallpaperItem? = null

        var iv1 = itemView.findViewById<ImageView>(R.id.iv1)
        var iv2 = itemView.findViewById<ImageView>(R.id.iv2)

        /********head view********/
        var headLayout = itemView.findViewById<RelativeLayout>(R.id.rl_head)
        var ivHeadImage = itemView.findViewById<ImageView>(R.id.iv_head)
        var tvHeadCatelog = itemView.findViewById<TextView>(R.id.tv_head_catelog)
        var tvHeadCount = itemView.findViewById<TextView>(R.id.tv_head_count)

        init {
            iv1.setOnClickListener(this)
            iv2.setOnClickListener(this)
            headLayout.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.iv1 -> goToPreview(itemForIv1)
                R.id.iv2 -> goToPreview(itemForIv2)
                R.id.rl_head -> goToPreview(itemHead)
            }
        }

        private fun goToPreview(wallpaperItem: WallpaperItem?) {
            val intent = Intent(itemView.context, PreviewActivity::class.java)
            intent.putExtra("flag", "online")
            intent.putExtra("wallpaperItem", wallpaperItem)
            itemView.context.startActivity(intent)
        }
    }


}