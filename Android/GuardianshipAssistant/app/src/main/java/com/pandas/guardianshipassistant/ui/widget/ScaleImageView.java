package com.pandas.guardianshipassistant.ui.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.pandas.guardianshipassistant.R;

/**
 *类功能  ：
 *@创建者 ： 青鹏
 * @QQ   ： 260498491
 *@date  ： 2020/11/17 12:31
 */
public final class ScaleImageView extends AppCompatImageView {

    private float mScaleSize = 1.2f;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScaleImageView);
        setScaleSize(array.getFloat(R.styleable.ScaleImageView_scaleRatio, mScaleSize));
        array.recycle();
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        // 判断当前手指是否按下了
        if (pressed) {
            setScaleX(mScaleSize);
            setScaleY(mScaleSize);
        } else {
            setScaleX(1);
            setScaleY(1);
        }
    }

    public void setScaleSize(float size) {
        mScaleSize = size;
    }
}