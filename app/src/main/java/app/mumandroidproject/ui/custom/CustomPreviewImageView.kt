package app.mumandroidproject.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
    private var translateX = -10000f
    private var currentX = 0f
    private var startX = 0f
    private var draggable = true

    private var rectCanTouchArea: RectF? = null

    fun setDraggable(draggable: Boolean) {
        this.draggable = draggable
    }

    fun getBitmapFromArea(): Bitmap? {
        var bitmapDrawable = drawable as BitmapDrawable
        val scale: Float = width.toFloat() / height.toFloat()
        var x = positonX.toInt()
        var width = (scale * drawable.intrinsicHeight).toInt()
        if (x + width > drawable.intrinsicWidth) {
            x = drawable.intrinsicWidth - width
        }
        return Bitmap.createBitmap(bitmapDrawable.bitmap, x, 0, width, drawable.intrinsicHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "zhq.debug  height: $height")
        Log.d(TAG, "zhq.debug  width: $width")
        rectCanTouchArea = RectF(0f, height - 500f, width.toFloat(), height.toFloat())
    }


    private var block = false
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                currentX = event.rawX
                startX = event.rawX
            }
            MotionEvent.ACTION_UP -> {
                if (block) {
                    block = false
                    return false
                }


            }
            MotionEvent.ACTION_MOVE -> {
                if (rectCanTouchArea?.contains(event.rawX, event.rawY)!!) {
                    block = true
                    currentX = event.rawX
                    translateX -= currentX - startX
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

            val scale = vHeight / dHeight.toFloat()

            mMatrix.setScale(scale, scale)
            if (translateX < -9999f) {
                translateX = (dWidth * scale - vWidth) / 2f
            }

            if (translateX > dWidth * scale - vWidth) {
                translateX = dWidth * scale - vWidth
            }

            if (translateX < 0) {
                translateX = 0f
            }
            positonX = translateX / scale
            mMatrix.postTranslate(-translateX, 0f)
            canvas.concat(mMatrix)
            drawable.draw(canvas)
        } else {
            super.onDraw(canvas)
        }
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        Log.d(TAG, "setImageBitmap ${bm?.width}")
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        Log.d(TAG, "setImageDrawable ${drawable?.intrinsicWidth}")
    }


}


