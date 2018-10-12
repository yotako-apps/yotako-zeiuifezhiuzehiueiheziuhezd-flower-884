package com.yotako.customView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.yotako.app.R;

/**
 * Created by Yotako on 23/03/2018.
 */

public class YotakoViewGroup extends ViewGroup {
    private Context context = null;
    private AttributeSet attrs = null;
    private Float border = null;
    private Float cornerRadius = null;
    private Float coef = 1.0f;
    private String color = null;
    private Integer originalHeight = 0;
    private Integer originalWidth = 0;

    public YotakoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        this.attrs = attrs;

        TypedArray ta = this.context.obtainStyledAttributes(this.attrs, R.styleable.YotakoViewGroup);

        this.cornerRadius = ta.getFloat(R.styleable.YotakoViewGroup_cornerRadius, 0);
        this.border = ta.getFloat(R.styleable.YotakoViewGroup_border, 0);
        this.color = ta.getString(R.styleable.YotakoViewGroup_shapeColor);
        this.originalHeight = ta.getInteger(R.styleable.YotakoCheckBox_originalHeight, 0);
        this.originalWidth = ta.getInteger(R.styleable.YotakoCheckBox_originalWidth, 0);
        setWillNotDraw(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        Resources r = getResources();

        Float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, r.getDisplayMetrics());
        Float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, r.getDisplayMetrics());

        View instance = findViewById(this.getId());
        try {
            ViewGroup.LayoutParams params = instance.getLayoutParams();

            if (this.originalHeight > 0 && this.originalWidth > 0) {
                this.coef = (height / this.originalHeight) > (width / this.originalWidth) ? (width / this.originalWidth) : (height / this.originalHeight);
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.cornerRadius != 0) {
            Paint p = new Paint();
            if (this.color != null && this.color != "") {
                p.setColor(Color.parseColor(color));
            }
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            p.setStrokeWidth(border);
            canvas.drawRoundRect(border, border, getWidth() , getHeight() , cornerRadius * coef , cornerRadius * coef, p);
        }
        super.onDraw(canvas);
    }
}
