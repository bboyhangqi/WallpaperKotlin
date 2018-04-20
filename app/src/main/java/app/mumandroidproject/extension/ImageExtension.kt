package app.mumandroidproject.extension

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import app.mumandroidproject.R
import app.mumandroidproject.helper.VolleyHelper
import com.android.volley.toolbox.NetworkImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/**
 * Created by CodingHome on 4/16/18.
 */
fun ImageView.loadByGlide(url: String?) {
    Glide.with(this.context)
            .asBitmap()
            .load(url)
            .transition(withCrossFade())
            .into(this)
}

fun ImageView.loadByGlide(url: String?, requestListener: RequestListener<Bitmap>) {
    Glide.with(this.context)
            .asBitmap()
            .load(url)
            .listener(requestListener)
            .transition(withCrossFade())
            .into(this)
}


fun ImageView.loadByGlide(rid: Int) {
    Glide.with(this.context)
            .asBitmap()
            .load(rid)
            .into(this)
}


fun NetworkImageView.loadImage(url: String?) {
    this.setImageUrl(url, VolleyHelper.getInstance(this.context).ivLoader)
}