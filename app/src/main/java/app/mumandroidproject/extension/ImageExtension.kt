package app.mumandroidproject.extension

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import app.mumandroidproject.R
import app.mumandroidproject.helper.VolleyHelper
import com.android.volley.toolbox.NetworkImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import java.io.File




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

fun ImageView.loadByGlideWithoutAnimation(url: String?) {
    Glide.with(this.context)
            .asBitmap()
            .load(url)
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
            .transition(withCrossFade())
            .into(this)
}

fun ImageView.loadByGlideFromLocal(url: String?) {
    Glide.with(this.context)
            .load(File(url))
            .into(this)
}


fun NetworkImageView.loadImageByVolley(url: String?) {
    this.setImageUrl(url, VolleyHelper.getInstance(this.context).ivLoader)
}

