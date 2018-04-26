package app.mumandroidproject.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import app.mumandroidproject.R;


/**
 * Created by zhaomingming-s on 2015/11/2.
 */
@SuppressLint("AppCompatCustomView")
public class CustomAnimImageView extends ImageView {

    private static final String TAG = "LocalImageView";

    private AlphaAnimation mInAnimatio;

    public CustomAnimImageView(Context context) {
        super(context);
    }

    public CustomAnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAnimImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return; // couldn't resolve the URI
        }
//        //绘画圆角区域
//        canvas.save();
//        Path path = new Path();
//        RectF rect1 = new RectF(0, 0, getWidth(), getHeight());
//        path.addRoundRect(rect1, 10,10, Path.Direction.CCW);
//        canvas.clipPath(path);
//        super.onDraw(canvas);
//        canvas.restore();
        //绘画圆角区域
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Log.d(TAG,"zhq.debug width: "+bitmap.getWidth());
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rectF, 12, 12, paint);
    }


    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (mInAnimatio == null) {
            mInAnimatio = new AlphaAnimation(0, 1.0f);
            mInAnimatio.setDuration(300);
        }
        this.startAnimation(mInAnimatio);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (mInAnimatio == null) {
            mInAnimatio = new AlphaAnimation(0, 1.0f);
            mInAnimatio.setDuration(300);
        }
        this.startAnimation(mInAnimatio);
    }
}
