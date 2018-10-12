package com.yotako.customView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.yotako.app.R;
import com.yotako.helper.ImageDownloadAsync;

/**
 * Created by Yotako on 20/03/2018.
 */

public class YotakoImageView extends android.support.v7.widget.AppCompatImageView {
    private Context context = null;
    private AttributeSet attrs = null;
    private String imageUrl = null;

    public YotakoImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        this.attrs = attrs;
        TypedArray ta = this.context.obtainStyledAttributes(this.attrs, R.styleable.YotakoImageView);

        this.imageUrl = ta.getString(R.styleable.YotakoImageView_customUrl);

        if (this.imageUrl != null) {
            ImageDownloadAsync downloadAsync = new ImageDownloadAsync(context, this);
            downloadAsync.execute(this.imageUrl);
        }

        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
