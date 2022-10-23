package cn.authing.api;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Looper;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;


public class LoadingButton extends AppCompatButton {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int OVER = 2;
    public static final int COVER = 3;

    protected AnimatedVectorDrawable loading;

    protected boolean showLoading;
    protected int loadingLocation;

    public LoadingButton(@NonNull Context context) {
        this(context, null);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.buttonStyle);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        loading = (AnimatedVectorDrawable) context.getDrawable(R.drawable.ic_authing_animated_loading_blue);
        loading.setVisible(false, true);

        int color = Color.parseColor("#396aff");

        loading.setTint(color);
    }

    public void startLoadingVisualEffect() {
        setEnabled(false);
        showLoading = true;
        loading.setVisible(true, true);
        loading.start();
    }

    public void stopLoadingVisualEffect() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            doStopLoadingVisualEffect();
        } else {
            post(this::doStopLoadingVisualEffect);
        }
    }

    private void doStopLoadingVisualEffect() {
        showLoading = false;
        loading.stop();
        loading.setVisible(false, true);
        setEnabled(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (loadingLocation == LEFT || loadingLocation == RIGHT) {
            drawLoading(canvas);
            super.onDraw(canvas);
        } else {
            if (loadingLocation != COVER || !showLoading) {
                super.onDraw(canvas);
            }
            drawLoading(canvas);
        }
    }

    private void drawLoading(Canvas canvas) {
        if (!showLoading) {
            return;
        }

        float cw = getWidth();
        float ch = getHeight();
        int p = (int) dp2px(getContext(), 4);
        int length = (int) (ch - 2 * p);
        loading.setBounds(0, 0, length, length);

        float x;
        float deltaX = 0;
        float textWidth = getPaint().measureText(getText().toString());
        float originalTextX = (cw - textWidth) / 2;
        if (loadingLocation == LEFT) {
            x = (cw - (length + p + textWidth)) / 2;
            float tx = x + length + p;
            deltaX = tx - originalTextX;
        } else if (loadingLocation == RIGHT) {
            x = (cw - (length - p - textWidth)) / 2;
            float tx = (cw - (length + p + textWidth)) / 2;
            deltaX = tx - originalTextX;
        } else {
            x = (cw - length) / 2;
        }

        canvas.save();
        canvas.translate(x, (ch - length) / 2);
        loading.draw(canvas);
        canvas.restore();

        canvas.translate(deltaX, 0);

        // continue onDraw while loading
        invalidate();
    }

    private float dp2px(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
