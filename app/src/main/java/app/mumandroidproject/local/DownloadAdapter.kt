package app.mumandroidproject.local

import android.content.Intent
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import app.mumandroidproject.R
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.extension.loadByGlideFromLocal
import app.mumandroidproject.preview.PreviewActivity
import app.mumandroidproject.ui.dialog.PresentationDialog

/**
 * Created by CodingHome on 4/22/18.
 */
class DownloadAdapter(var data: Array<LocalImageItem>, windowManager: WindowManager, var localPresenter: LocalPresenter) : RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {

    private val TAG = "DownloadAdpter"

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    private var itemList = mutableListOf<Array<LocalImageItem>>()


    init {
        Log.d(TAG, "zhq.debug init")
        val display = windowManager.getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        for ((index, value) in data.withIndex()) {
            if (index + 1 == data.size) {
                if (index % 2 == 0) itemList.add(arrayOf(data[index]))
            } else {
                if (index % 2 == 0) itemList.add(arrayOf(data[index], data[index + 1]))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_item, parent, false)
        return ViewHolder(view, localPresenter)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "zhq.debug onBindViewHolder")
        val imageWidth: Int
        holder.iv1.visibility = View.VISIBLE
        imageWidth = screenWidth / 2 - 30
        holder.iv1.layoutParams = RelativeLayout.LayoutParams(imageWidth, imageWidth)
        holder.iv1.loadByGlideFromLocal(itemList[position][0].path)
        holder.itemForIv1 = itemList[position][0]
        if (itemList[position].size == 2) {
            holder.iv2.visibility = View.VISIBLE
            holder.iv2.layoutParams = RelativeLayout.LayoutParams(imageWidth, imageWidth)
            holder.iv2.loadByGlideFromLocal(itemList[position][1].path)
            holder.itemForIv2 = itemList[position][1]
        }
    }

    class ViewHolder(itemView: View, var localPresenter: LocalPresenter) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var itemForIv1: LocalImageItem? = null
        var itemForIv2: LocalImageItem? = null

        var iv1 = itemView.findViewById<ImageView>(R.id.iv1)
        var iv2 = itemView.findViewById<ImageView>(R.id.iv2)

        init {
            iv1.setOnClickListener(this)
            iv2.setOnClickListener(this)
            iv1.setOnLongClickListener { deleteImage(itemForIv1) }
            iv2.setOnLongClickListener { deleteImage(itemForIv2) }
        }

        private fun deleteImage(localImageItem: LocalImageItem?): Boolean {

            val dialog = PresentationDialog(itemView.context)
            dialog.setClickListener(object : PresentationDialog.OnPresentationDialogClickListener {
                override fun onCancelClick() {
                }
                override fun onOkClick() {
                    localPresenter.deleteDownloadImage(itemView.context,localImageItem)
                }
            })
            dialog.show()
            return true
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


