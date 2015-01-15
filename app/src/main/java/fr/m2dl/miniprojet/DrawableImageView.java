package fr.m2dl.miniprojet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by mfaure on 15/01/15.
 */
public class DrawableImageView extends ImageView {

    private Canvas canvas;
    private Paint p;
    private float x=-10;
    private float y=-10;

    public DrawableImageView(Context context) {
        super(context);
    }

    public DrawableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
        p.setStrokeWidth(10);

        if(x>0 && y>0){
            canvas.drawLine(x-30, y-30, x+30, y+30, p);
            canvas.drawLine(x+30, y-30, x-30, y+30, p);
        }
    }

    public void draw(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }

    public void dismissMark(){
        x=-10;
        y=-10;
        invalidate();
    }
}
