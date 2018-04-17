package app.mumandroidproject.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by CodingHome on 4/16/18.
 */
fun ImageView.loadByGlide(url: String?) {
    Glide.with(this.context)
            .asBitmap()
            .load(url)
            .into(this)
}

fun ImageView.loadByGlide(rid: Int) {
    Glide.with(this.context)
            .asBitmap()
            .load(rid)
            .into(this)
}