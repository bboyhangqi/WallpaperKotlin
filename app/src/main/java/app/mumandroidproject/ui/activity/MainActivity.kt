package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import app.mumandroidproject.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    companion object {
        private const val UI_ANIMATION_DELAY: Long = 300
        private const val UI_QUIT_DELAY: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        hide()
    }

    private fun hide() {
        supportActionBar?.hide()
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY)
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.main_nav_host_fragment).navigateUp()

    private var backPressedBlock = true
    override fun onBackPressed() {
        if (backPressedBlock) {
            backPressedBlock = false
            Handler().postDelayed({ backPressedBlock = true }, UI_QUIT_DELAY)
            Toast.makeText(this, "Please press again to leave", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }


}
