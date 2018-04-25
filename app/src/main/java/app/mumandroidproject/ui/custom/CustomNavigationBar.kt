package app.mumandroidproject.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import app.mumandroidproject.R
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.navigation_bar.view.*

/**
 * Created by CodingHome on 4/16/18.
 */
class CustomNavigationBar(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    init {
        var view = LayoutInflater.from(context).inflate(R.layout.navigation_bar, this, false)
        addView(view)
    }

    fun startAnimation() {
        val bgAnimatorHot = ObjectAnimator.ofFloat(iv_nvi_hot, "translationY", iv_nvi_hot.top.toFloat() - iv_nvi_hot.height / 2, iv_nvi_hot.top.toFloat() + 20f)
        val bgAnimatorCategory = ObjectAnimator.ofFloat(iv_nvi_category, "translationY", iv_nvi_category.top.toFloat()- iv_nvi_category.top / 2, iv_nvi_category.top.toFloat() + 20f)
        val bgAnimatorLocal = ObjectAnimator.ofFloat(iv_nvi_local, "translationY", iv_nvi_local.top.toFloat()- iv_nvi_local.top / 2, iv_nvi_local.top.toFloat() + 20f)
        bgAnimatorHot.duration = 3000
        bgAnimatorHot.repeatCount = ObjectAnimator.INFINITE
        bgAnimatorHot.repeatMode = ObjectAnimator.REVERSE
        bgAnimatorHot.start()
        bgAnimatorCategory.duration = 3000
        bgAnimatorCategory.repeatCount = ObjectAnimator.INFINITE
        bgAnimatorCategory.repeatMode = ObjectAnimator.REVERSE
        bgAnimatorCategory.startDelay = 1000
        bgAnimatorCategory.start()
        bgAnimatorLocal.duration = 3000
        bgAnimatorLocal.repeatCount = ObjectAnimator.INFINITE
        bgAnimatorLocal.repeatMode = ObjectAnimator.REVERSE
        bgAnimatorLocal.startDelay = 2000
        bgAnimatorLocal.start()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }


    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
    }

    override fun onWindowSystemUiVisibilityChanged(visible: Int) {
        super.onWindowSystemUiVisibilityChanged(visible)
    }


}