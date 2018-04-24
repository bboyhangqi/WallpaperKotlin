package app.mumandroidproject.ui.adpter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.mumandroidproject.R
import app.mumandroidproject.bean.WallpaperCategory
import app.mumandroidproject.ui.activity.ColumnActivity

/**
 * Created by CodingHome on 4/16/18.
 */
class CategoryAdapter(var data: List<WallpaperCategory>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(data[position].rId)
        holder.name.text = data[position].name
        holder.count.text = data[position].count
        holder.itemView.findViewById<View>(R.id.root).setOnClickListener { onClick(it, data[position].category.toUpperCase()) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    fun onClick(view: View, category: String) {
        var intent = Intent(view.context, ColumnActivity::class.java)
        intent.putExtra("category", category)
        view.context.startActivity(intent)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.iv)
        var name = itemView.findViewById<TextView>(R.id.name)
        var count = itemView.findViewById<TextView>(R.id.count)
    }
}