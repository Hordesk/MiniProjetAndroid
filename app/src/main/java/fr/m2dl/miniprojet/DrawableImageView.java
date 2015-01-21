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

    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float xTopLeft = -10;
    private float yTopLeft = -10;
    private float xBottomRight = -10;
    private float yBottomRight = -10;

    public DrawableImageView(Context context) {
        super(context);
    }

    public DrawableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);

        if(xTopLeft >0 && yTopLeft >0){
            switch (StaticData.tool) {
                case CROSS:
                    canvas.drawLine(xTopLeft - 30, yTopLeft - 30, xTopLeft + 30, yTopLeft + 30, p);
                    canvas.drawLine(xTopLeft + 30, yTopLeft - 30, xTopLeft - 30, yTopLeft + 30, p);
                    break;
                case SQUARE:
                    canvas.drawRect(xTopLeft, yTopLeft, xBottomRight, yBottomRight, p);
                    break;
                default:
                    // NONE
                    break;
            }
        }
    }

    public void drawPoint(float x, float y) {
        StaticData.tool = ToolStatus.CROSS;
        this.xTopLeft = x;
        this.yTopLeft = y;
        invalidate();
    }

    public void drawRect(float xTopLeft, float yTopLeft, float xBottomRight, float yBottomRight) {
        StaticData.tool = ToolStatus.SQUARE;
        this.xTopLeft = xTopLeft;
        this.yTopLeft = yTopLeft;
        this.xBottomRight = xBottomRight;
        this.yBottomRight = yBottomRight;
        invalidate();
    }

    public void dismissMark(){
        xTopLeft =-10;
        yTopLeft =-10;
        invalidate();
    }
}
