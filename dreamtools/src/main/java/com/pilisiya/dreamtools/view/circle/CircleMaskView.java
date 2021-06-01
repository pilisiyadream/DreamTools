package com.pilisiya.dreamtools.view.circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.pilisiya.dreamtools.R;


public class CircleMaskView extends AppCompatImageView {

    private int mCenterX, mCenterY;
    private Paint mCirclePaint;
    private Paint mCircleBorderPaint;
    private Paint mBoildPaint;

    private int mWidth = 0;
    private int mHeight = 0;
    private int mBackgroundColor = 0xBF000000;
    private int mFrameBackgroundColor = 0x6400A2E8;

    public CircleMaskView(Context context) {
        super(context);
        init();
    }

    public CircleMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleTypedArray(context,attrs);
        init();
    }

    public CircleMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleTypedArray(context,attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskView);
        mWidth = typedArray.getDimensionPixelSize(R.styleable.MaskView_mv_width, -1);
        mHeight = typedArray.getDimensionPixelSize(R.styleable.MaskView_mv_height, -1);
        mBackgroundColor = typedArray.getColor(R.styleable.MaskView_mv_backgroundColor, mBackgroundColor);
        mFrameBackgroundColor = typedArray.getColor(R.styleable.MaskView_mv_FrameBackgroundColor, mFrameBackgroundColor);
        typedArray.recycle();
    }
    private void init(){
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(mBackgroundColor);
        // mCirclePaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
        mBoildPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoildPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBoildPaint.setColor(mBackgroundColor);

        mCircleBorderPaint = new Paint();
        mCircleBorderPaint.setStyle(Paint.Style.STROKE);
        mCircleBorderPaint.setColor(mFrameBackgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = getShowRect(canvas,mWidth,mHeight);
        //drawBoild(canvas,rect);
        drawCircle(canvas,rect);
        //Resources res = DetectedApplication.instance().getResources();
        //Bitmap bmp= BitmapFactory.decodeResource(res, R.drawable.face_main_camera_mask);
        //canvas.drawBitmap(bmp,rect.left,rect.top,null);
    }

    /**
     *
     * *****************************************
     * *   (x1,y1)                             *
     * *       A**********************         *
     * *       *                     *         *
     * *       *                     *         *
     * *       *                     *         *
     * *       **********************B         *
     * *                           （x2,y2）   *
     * *****************************************
     *
     * @param canvas
     * @param rect
     */
    private void drawBoild(Canvas canvas ,Rect rect){
        int x1 = rect.left;
        int y1 = rect.top;
        int x2 = rect.right;
        int y2 = rect.bottom;
        Rect rect1 = new Rect(0,0,x1,canvas.getHeight());
        Rect rect2 = new Rect(0,0,canvas.getWidth(),y1);
        Rect rect3 = new Rect(x1,y2,canvas.getWidth(),canvas.getHeight());
        Rect rect4 = new Rect(x2,y1,canvas.getWidth(),canvas.getHeight());
        canvas.drawRect(rect1,mBoildPaint);
        canvas.drawRect(rect2,mBoildPaint);
        canvas.drawRect(rect3,mBoildPaint);
        canvas.drawRect(rect4,mBoildPaint);
    }
    private void drawCircle(Canvas canvas ,Rect rect){
        int x1 = rect.left;
        int y1 = rect.top;
        int x2 = rect.right;
        int y2 = rect.bottom;
        int w = x2 - x1;
        int h = y2 - y1;
        int cx = x1+w/2;
        int cy = y1+h/2;
        int d = ((cx - x1) < (cy - y1)) ? (cx - x1) : (cy - y1);
        float paintWidth = 1000;
        float R = d + paintWidth/2;
        mCirclePaint.setStrokeWidth(paintWidth);
        mCircleBorderPaint.setStrokeWidth(8);
        canvas.drawCircle(cx,cy,R,mCirclePaint);
        canvas.drawCircle(cx,cy,d + 4,mCircleBorderPaint);
    }
    private Rect getShowRect(Canvas canvas,int width,int height){
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        if(w <= width ){
            width = w;
        }
        if(h <= height){
            height = h;
        }
        int x1 = (w - width)/2;
        int y1 = (h - height)/2;
        return  new Rect(x1,y1,x1+width,y1+height);
    }
}
