package app.mumandroidproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.mumandroidproject.R;


public class LoadingDialog extends Dialog {

    private static final String TAG = "LoadingDialog";

    private TextView mTv;
    private ImageView mIv;
    private ImageView mProcessIv;
    private AnimationDrawable animationDrawable;
    private View mRootView;
    private int mCurMode;

    public static final int MODE_LOADING_WALLPAPER = 0;
    public static final int MODE_SETTING_WALLPAPER = 1;
    public static final int MODE_FINISH_SETTING_WALLPAPER = 2;
    public static final int MODE_ERROR_SETTING_WALLPAPER = 3;
    public static final int MODE_ERROR_DOWNLOAD_WALLPAPER = 4;
    public static final int MODE_FINISH_DOWNLOAD_WALLPAPER = 5;

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        View contentView = getLayoutInflater().inflate(R.layout.dialog_loading_layout, null, false);
        mRootView = contentView.findViewById(R.id.root_view);
        setContentView(contentView);
        init();
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        View contentView = getLayoutInflater().inflate(R.layout.dialog_loading_layout, null, false);
        mRootView = contentView.findViewById(R.id.root_view);
        setContentView(contentView);
        init();
    }

    private void init() {
        mTv = (TextView) findViewById(R.id.tv);
        mIv = (ImageView) findViewById(R.id.iv);
        mProcessIv = (ImageView) findViewById(R.id.pb);
        animationDrawable = (AnimationDrawable) mProcessIv.getDrawable();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public int getMode(){
        return mCurMode;
    }

    public void swithMode(int mode){
        mCurMode=mode;
        switch (mode){
            case MODE_LOADING_WALLPAPER:
                mTv.setText(getContext().getString(R.string.loading_wallpaper));
                mProcessIv.setVisibility(View.VISIBLE);
                animationDrawable.start();
                mIv.setVisibility(View.INVISIBLE);
                break;
            case MODE_SETTING_WALLPAPER:
                mTv.setText(getContext().getString(R.string.setting_wallpaper));
                mProcessIv.setVisibility(View.VISIBLE);
                animationDrawable.start();
                mIv.setVisibility(View.INVISIBLE);
                break;
            case MODE_FINISH_SETTING_WALLPAPER:
                mTv.setText(getContext().getString(R.string.finish_setting_wallpaper));
                animationDrawable.stop();
                mProcessIv.setVisibility(View.INVISIBLE);
                mIv.setVisibility(View.VISIBLE);
                mIv.setImageResource(R.drawable.finish_setting_wallpaper);
                break;
            case MODE_ERROR_SETTING_WALLPAPER:
                mTv.setText(getContext().getString(R.string.error_setting_wallpaper));
                animationDrawable.stop();
                mProcessIv.setVisibility(View.INVISIBLE);
                mIv.setVisibility(View.VISIBLE);
                mIv.setImageResource(R.drawable.dialog_download_error);
                break;
            case MODE_ERROR_DOWNLOAD_WALLPAPER:
                mTv.setText(getContext().getString(R.string.error_download_wallpaper));
                animationDrawable.stop();
                mProcessIv.setVisibility(View.INVISIBLE);
                mIv.setVisibility(View.VISIBLE);
                mIv.setImageResource(R.drawable.dialog_download_error);
                break;
            case MODE_FINISH_DOWNLOAD_WALLPAPER:
                mTv.setText(getContext().getString(R.string.finish_download_wallpaper));
                animationDrawable.stop();
                mProcessIv.setVisibility(View.INVISIBLE);
                mIv.setVisibility(View.VISIBLE);
                mIv.setImageResource(R.drawable.finish_setting_wallpaper);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if(mCallBack!=null) mCallBack.onDialogBackPressed();
    }

    private DialogBackPressedCallBack mCallBack;

    public void setBackPressedCallBack(DialogBackPressedCallBack pressedCallBack){
        mCallBack=pressedCallBack;
    }

    public interface DialogBackPressedCallBack{
        void onDialogBackPressed();
    }

}
