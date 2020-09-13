package ru.geekbrains.android3.weather1.presentation.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import ru.geekbrains.android3.weather1.R;

public class CustomView extends View {
    private final static String TAG = "CustomView";
    public static final int BEAM_FACTOR = 7;
    public static final int SUN_FACTOR = 4;
    private Paint paint;
    private int customColor;

    public CustomView(Context context) {
        super(context);
        Log.d(TAG, "Constructor1");
        initView(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "Constructor2");
        initView(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "Constructor3");
        initView(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "Constructor4");
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        customColor = ta.getColor(R.styleable.CustomView_custom_color, Color.CYAN);
        ta.recycle();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setColor(customColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");
        int width = 0;
        int height = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.d(TAG, "widthSize: " + widthSize);

        if (widthMode == MeasureSpec.AT_MOST) { //wrap_content
            width = Math.min(300, widthSize);
            Log.d(TAG, "width AT_MOST: " + width);
        } else if (widthMode == MeasureSpec.EXACTLY) { // конкретное значение либо match_parent
            width = Math.min(300, widthSize);
            //width = widthSize;
            Log.d(TAG, "width EXACTLY: " + width);
        } else if (widthMode == MeasureSpec.UNSPECIFIED) { // если прокрутка, неопределено
            width = widthSize;
            Log.d(TAG, "width UNSPECIFIED: " + width);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "heightSize: " + heightSize);

        if (heightMode == MeasureSpec.AT_MOST) { //wrap_content
            height = Math.min(300, heightSize);
            Log.d(TAG, "height AT_MOST: " + height);
        } else if (heightMode == MeasureSpec.EXACTLY) { // конкретное значение либо match_parent
            height = Math.min(300, heightSize);
            //height = heightSize;
            Log.d(TAG, "height EXACTLY: " + height);
        } else if (heightMode == MeasureSpec.UNSPECIFIED) { // если прокрутка, неопределено
            height = heightSize;
            Log.d(TAG, "height UNSPECIFIED: " + height);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: {
                Toast.makeText(getContext(), "MotionEvent.ACTION_UP", Toast.LENGTH_SHORT).show();
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                changeColor();
                break;
            }
            default:
                return false;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / SUN_FACTOR, paint);
        canvas.drawLine(0, getHeight() / 2,
                getWidth(), getHeight() / 2, paint);
        canvas.drawLine(getWidth() / 2, 0,
                getWidth() / 2, getHeight(), paint);
        canvas.drawLine(getWidth() / BEAM_FACTOR, getHeight() / BEAM_FACTOR,
                getWidth() - getWidth() / BEAM_FACTOR, getHeight() - getHeight() / BEAM_FACTOR, paint);
        canvas.drawLine(getWidth() / BEAM_FACTOR, getHeight() - getHeight() / BEAM_FACTOR,
                getWidth() - getWidth() / BEAM_FACTOR, getHeight() / BEAM_FACTOR, paint);
    }

    void changeColor() {
        if (paint.getColor() == getResources().getColor(R.color.red_sun))
            paint.setColor(getResources().getColor(R.color.yellow_sun));
        else paint.setColor(getResources().getColor(R.color.red_sun));
        postInvalidate();
    }
}
