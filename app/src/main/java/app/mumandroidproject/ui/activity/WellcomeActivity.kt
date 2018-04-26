package app.mumandroidproject.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.mumandroidproject.R
import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import kotlinx.android.synthetic.main.activity_wellcome.*

class WellcomeActivity : AppCompatActivity(), Animator.AnimatorListener {

    private val bgScaleSet by lazy { AnimatorSet() }
    private val bgAnimatorX by lazy { ObjectAnimator.ofFloat(iv_splash, "scaleX", 1.3f, 1.0f) }
    private val bgAnimatorY by lazy { ObjectAnimator.ofFloat(iv_splash, "scaleY", 1.3f, 1.0f) }

    private val logoAnimator by lazy { ObjectAnimator.ofFloat(rl_logo, "alpha", 0f, 1.0f) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)
    }


    override fun onStart() {
        super.onStart()
        startAnimation()
    }

    private fun startAnimation() {
        iv_splash.visibility = View.VISIBLE
        bgAnimatorX.duration = 3000
        bgAnimatorY.duration = 3000
        bgScaleSet.addListener(this)
        bgScaleSet.play(bgAnimatorX).with(bgAnimatorY)
        bgScaleSet.start()

        logoAnimator.duration = 1000
        logoAnimator.addListener(this)
        logoAnimator.startDelay = 1000
        logoAnimator.start()
    }


    override fun onAnimationRepeat(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
        if(animation==bgScaleSet){
            startActivity(Intent(this, EntryActivity::class.java))
            finish()
        }

    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationStart(animation: Animator?) {
        if (animation == logoAnimator) {
            rl_logo.visibility = View.VISIBLE
        }
    }


}
