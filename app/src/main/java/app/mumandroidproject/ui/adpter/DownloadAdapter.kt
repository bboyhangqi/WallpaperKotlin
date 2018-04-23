package app.mumandroidproject.ui.adpter

import android.content.Intent
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import app.mumandroidproject.R
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.extension.loadByGlideFromLocal
import app.mumandroidproject.ui.activity.PreviewActivity

/**
 * Created by CodingHome on 4/22/18.
 */
class DownloadAdapter(var data: List<LocalImageItem?>, windowManager: WindowManager) : RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {

    private val TAG = "DownloadAdpter"

    var screenWidth: Int = 0
    var screenHeight: Int = 0

    var itemList = mutableListOf<Array<LocalImageItem?>>()


    init {
        val display = windowManager.getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        data.forEachIndexed { index, item ->
            if (index % 2 == 0) {
                if (index + 1 < data.size)
                    itemList.add(arrayOf(data[index], data[index + 1]))
                else
                    itemList.add(arrayOf(data[index]))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return (data.size) / 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageWidth: Int
        holder.iv2.visibility = View.VISIBLE
        holder.iv1.visibility = View.VISIBLE
        imageWidth = screenWidth / 2
        holder.iv1.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
        holder.iv1.setPadding(0, 2, 2, 2)
        holder.iv2.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
        holder.iv2.setPadding(2, 2, 0, 2)
        Log.d(TAG, "..zhq.debug...onBindViewHolder...path  ${itemList[position][0]?.path}")
        Log.d(TAG, "..zhq.debug...onBindViewHolder...path  ${itemList[position][1]?.path}")
        holder.iv1.loadByGlideFromLocal(itemList[position][0]?.path)
        holder.iv2.loadByGlideFromLocal(itemList[position][1]?.path)
        holder.itemForIv1 = itemList[position][0]
        holder.itemForIv2 = itemList[position][1]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var itemForIv1: LocalImageItem? = null
        var itemForIv2: LocalImageItem? = null

        var iv1 = itemView.findViewById<ImageView>(R.id.iv1)
        var iv2 = itemView.findViewById<ImageView>(R.id.iv2)

        init {
            iv1.setOnClickListener(this)
            iv2.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.iv1 -> goToPreview(itemForIv1)
                R.id.iv2 -> goToPreview(itemForIv2)
            }
        }

        private fun goToPreview(item: LocalImageItem?) {
            val intent = Intent(itemView.context, PreviewActivity::class.java)
            intent.putExtra("flag", "local")
            intent.putExtra("path", item?.path)
            itemView.context.startActivity(intent)
        }
    }
}


