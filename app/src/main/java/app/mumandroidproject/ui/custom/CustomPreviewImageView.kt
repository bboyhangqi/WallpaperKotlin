package app.mumandroidproject.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.TransitionDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView

/**
 * Created by CodingHome on 4/25/18.
 */
class CustomPreviewImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {

    private val TAG = "CustomPreviewImageView"

    private var mMatrix = Matrix()
    private var positonX = -10000f
    private var currentX = 0f
    private var startX = 0f
    private var offsetX = 0f
    private var draggable = true

    fun setDraggable(draggable: Boolean) {
        this.draggable = draggable
    }

    fun getBitmapFromArea(): Bitmap? {
        var bitmapDrawable = drawable as BitmapDrawable
        return Bitmap.createBitmap(bitmapDrawable.bitmap, 0, 0, width, bitmapDrawable.bitmap.height)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                currentX = event.rawX
                startX = event.rawX
            }
            MotionEvent.ACTION_UP -> {
            }
            MotionEvent.ACTION_MOVE -> {
                if (draggable) {
                    currentX = event.rawX
                    offsetX = currentX - startX
                    positonX -= offsetX
                    invalidate()
                    startX = event.rawX
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable != null) {
            val vHeight = height.toFloat()
            val vWidth = width.toFloat()
            val dHeight = drawable.intrinsicHeight
            val dWidth = drawable.intrinsicWidth

            Log.d(TAG, "zhq.debug dWidth: $dWidth")
            Log.d(TAG, "zhq.debug vWidth: $vWidth")
            Log.d(TAG, "zhq.debug positonX: $positonX")
            val scale = vHeight / dHeight.toFloat()
            mMatrix.setScale(scale, scale)
            if (positonX < -9999f) {
                positonX = dWidth / 2f
            }

            if (positonX > vWidth) {
                positonX = vWidth
            }

            if (positonX < 0) {
                positonX = 0f
            }
            mMatrix.postTranslate(-positonX, 0f)
            canvas.concat(mMatrix)
            drawable.draw(canvas)
        } else {
            super.onDraw(canvas)
        }
    }


}


