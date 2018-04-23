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

/**
 * Created by CodingHome on 4/16/18.
 */
class HotAdapter(var data: MutableList<WallpaperItem>) : RecyclerView.Adapter<HotAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Nhan123", data.toString())
        holder.imageView.loadByGlide(data[position].url)
        holder.date.text = data[position].date
        holder.description.text = data[position].dec
        holder.like.text = data[position].like
//        holder.itemView.findViewById<View>(R.id.root).setOnClickListener { onClick(it, data[position].requestId, data[position].name) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.hot_item, parent, false)
        return ViewHolder(view)
    }

    fun onClick(view: View, category: String) {
        var intent = Intent(view.context, ColumnActivity::class.java)
        intent.putExtra("category", category)
        view.context.startActivity(intent)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.iv)
        var date = itemView.findViewById<TextView>(R.id.date)
        var description = itemView.findViewById<TextView>(R.id.description)
        var like = itemView.findViewById<TextView>(R.id.like)
    }
}