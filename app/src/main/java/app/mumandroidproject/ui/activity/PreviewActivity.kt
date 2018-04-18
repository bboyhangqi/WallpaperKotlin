package app.mumandroidproject.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.mumandroidproject.R

class PreviewActivity : AppCompatActivity() {

    private val TAG = "PreviewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        Log.d(TAG,"url: "+intent.getStringExtra("url"))
    }
}
