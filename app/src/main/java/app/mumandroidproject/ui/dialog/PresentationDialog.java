package app.mumandroidproject.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import app.mumandroidproject.R;

public class PresentationDialog extends Dialog implements View.OnClickListener, DialogInterface.OnCancelListener{

    private static final String TAG = "PresentationDialog";
    private TextView mCancelTv;
    private TextView mOkTv;
    private TextView mContentTv;
    private View mContentView;

    private ObjectAnimator mAlphaInAnimator;
    private ObjectAnimator mAlphaOutAnimator;

    private static final int ANIMATION_FLAG_CANCEL_CLICK = 0;
    private static final int ANIMATION_FLAG_OK_CLICK = 1;
    private int curClickFlag;

    public PresentationDialog(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        mContentView = getLayoutInflater().inflate(R.layout.dialog_presentation_layout, null, false);
        setContentView(mContentView);
        init();
    }

    public PresentationDialog(Context context, int theme) {
        super(context, theme);
        View contentView = getLayoutInflater().inflate(R.layout.dialog_presentation_layout, null, false);
        setContentView(contentView);
        init();
    }

    private OnPresentationDialogClickListener mClickLinstener ;

    public void setClickListener(OnPresentationDialogClickListener clickListener){
        mClickLinstener=clickListener;
    }

    public void setContent(String text){
        mContentTv.setText(text);
    }

    private void init (){
        mContentTv=(TextView)findViewById(R.id.content_tv);
        mCancelTv=(TextView)findViewById(R.id.btn_cancel);
        mOkTv=(TextView)findViewById(R.id.btn_ok);
        mCancelTv.setOnClickListener(this);
        mOkTv.setOnClickListener(this);
        RelativeLayout dialogLayout = (RelativeLayout)findViewById(R.id.dialog_layout);
        mAlphaInAnimator = ObjectAnimator.ofFloat(dialogLayout, "alpha", 0, 1);
        mAlphaInAnimator.setDuration(200);
        mAlphaOutAnimator = ObjectAnimator.ofFloat(dialogLayout, "alpha", 1, 0);
        mAlphaOutAnimator.setDuration(200);
        mAlphaOutAnimator.addListener(outAnimatorListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAlphaInAnimator.start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_cancel:
                mAlphaOutAnimator.start();
                curClickFlag=ANIMATION_FLAG_CANCEL_CLICK;
                break;
            case R.id.btn_ok:
                mAlphaOutAnimator.start();
                curClickFlag=ANIMATION_FLAG_OK_CLICK;
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dialog.dismiss();
    }


    public interface OnPresentationDialogClickListener {
        void onCancelClick();
        void onOkClick();
    }


    private Animator.AnimatorListener outAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            switch (curClickFlag){
                case ANIMATION_FLAG_CANCEL_CLICK :
                    if (mClickLinstener != null) mClickLinstener.onCancelClick();
                    dismiss();
                    break;
                case ANIMATION_FLAG_OK_CLICK :
                    if(mClickLinstener!=null) mClickLinstener.onOkClick();
                    dismiss();
                    break;
                default:
                    dismiss();
                    break;
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };


}
