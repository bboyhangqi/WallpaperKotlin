package app.mumandroidproject.ui.adpter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperItem
import app.mumandroidproject.extension.loadByGlide
import app.mumandroidproject.ui.activity.ColumnActivity
import app.mumandroidproject.ui.activity.PreviewActivity
import java.util.*

/**
 * Created by CodingHome on 4/16/18.
 */
class HotAdapter(var data: MutableList<WallpaperItem>) : RecyclerView.Adapter<HotAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.loadByGlide(data[position].url)
        holder.date.text = data[position].date.substring(3, 5)
        holder.month.text = convertDate(data[position].date.substring(0, 2))
        holder.description.text = data[position].desc
        holder.like.text = data[position].like.toString()
        holder.itemView.findViewById<View>(R.id.root).setOnClickListener { onClick(it, data[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.hot_item, parent, false)
        return ViewHolder(view)
    }

    fun onClick(view: View, wallpaperItem: WallpaperItem) {
        val intent = Intent(view.context, PreviewActivity::class.java)
        intent.putExtra("flag", "online")
        intent.putExtra("wallpaperItem", wallpaperItem)
        view.context.startActivity(intent)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.iv)
        var date = itemView.findViewById<TextView>(R.id.date)
        var month = itemView.findViewById<TextView>(R.id.month)
        var description = itemView.findViewById<TextView>(R.id.description)
        var like = itemView.findViewById<TextView>(R.id.like)
    }

    fun convertDate(dateInput: String): String {
        when (dateInput.toInt()) {
            1 -> {
                return "January"
            }
            2 -> {
                return "February"
            }
            3 -> {
                return "March"
            }
            4 -> {
                return "April"
            }
            5 -> {
                return "May"
            }
            6 -> {
                return "June"
            }
            7 -> {
                return "July"
            }
            8 -> {
                return "August"
            }
            9 -> {
                return "September"
            }
            10 -> {
                return "October"
            }
            11 -> {
                return "November"
            }
            12 -> {
                return "December"
            }
        }

        return "January"
    }
}