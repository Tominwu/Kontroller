package com.github.roarappstudio.btkontroller;/*
 * Copyright 2012 Lars Werkman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;


import com.github.roarappstudio.btkontroller.senders.RadialSender2;


public class WheelPicker extends View {
    /*
     * Constants used to save/restore the instance state.
     */
    private static final String STATE_PARENT = "parent";
    private static final String STATE_ANGLE = "angle";
    private static final String STATE_OLD_COLOR = "color";
    private static final String STATE_SHOW_OLD_COLOR = "showColor";
    /**
     * Colors to construct the color wheel using {@link SweepGradient}.
     */
    private static final int[] COLORS = new int[]{0xFFFF0000, 0xFFFF00FF,
            0xFF0000FF, 0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};

    /**
     * {@code Paint} instance used to draw the color wheel.
     */
    private Paint mColorWheelPaint;

    /**
     * {@code Paint} instance used to draw the pointer's "halo".
     */
    private Paint mPointerHaloPaint;

    /**
     * {@code Paint} instance used to draw the pointer (the selected color).
     */
    private Paint mPointerColor;

    /**
     * The width of the color wheel thickness.
     */
    private int mColorWheelThickness;

    /**
     * The radius of the color wheel.
     */
    private int mColorWheelRadius;
    private int mPreferredColorWheelRadius;

    /**
     * The radius of the center circle inside the color wheel.
     */
    private int mColorCenterRadius;
    private int mPreferredColorCenterRadius;

    /**
     * The radius of the halo of the center circle inside the color wheel.
     */
    private int mColorCenterHaloRadius;
    private int mPreferredColorCenterHaloRadius;

    /**
     * The radius of the pointer.
     */
    private int mColorPointerRadius;

    /**
     * The radius of the halo of the pointer.
     */
    private int mColorPointerHaloRadius;

    /**
     * The rectangle enclosing the color wheel.
     */
    private RectF mColorWheelRectangle = new RectF();

    /**
     * The rectangle enclosing the center inside the color wheel.
     */
    private RectF mCenterRectangle = new RectF();

    /**
     * {@code true} if the user clicked on the pointer to start the move mode. <br>
     * {@code false} once the user stops touching the screen.
     *
     * @see #onTouchEvent(MotionEvent)
     */
    private boolean mUserIsMovingPointer = false;

    /**
     * The ARGB value of the currently selected color.
     */
    private int mColor;

    /**
     * The ARGB value of the center with the old selected color.
     */
    private int mCenterOldColor;

    /**
     * Whether to show the old color in the center or not.
     */
    private boolean mShowCenterOldColor;

    /**
     * The ARGB value of the center with the new selected color.
     */
    private int mCenterNewColor;

    /**
     * Number of pixels the origin of this view is moved in X- and Y-direction.
     *
     * <p>
     * We use the center of this (quadratic) View as origin of our internal
     * coordinate system. Android uses the upper left corner as origin for the
     * View-specific coordinate system. So this is the value we use to translate
     * from one coordinate system to the other.
     * </p>
     *
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     *
     * @see #onDraw(Canvas)
     */
    private float mTranslationOffset;

    /**
     * Distance between pointer and user touch in X-direction.
     */
    private float mSlopX;

    /**
     * Distance between pointer and user touch in Y-direction.
     */
    private float mSlopY;

    /**
     * The pointer's position expressed as angle (in rad).
     */
    private float mAngle;

    /**
     * {@code Paint} instance used to draw the center with the old selected
     * color.
     */
    private Paint mCenterOldPaint;

    /**
     * {@code Paint} instance used to draw the center with the new selected
     * color.
     */
    private Paint mCenterNewPaint;

    /**
     * {@code Paint} instance used to draw the halo of the center selected
     * colors.
     */
    private Paint mCenterHaloPaint;

    /**
     * An array of floats that can be build into a {@code Color} <br>
     * Where we can extract the Saturation and Value from.
     */
    private float[] mHSV = new float[3];

    /**
     * {@code SVBar} instance used to control the Saturation/Value bar.
     */


    /**
     * {@code TouchAnywhereOnColorWheelEnabled} instance used to control <br>
     * if the color wheel accepts input anywhere on the wheel or just <br>
     * on the halo.
     */
    private boolean mTouchAnywhereOnColorWheelEnabled = true;


    /**
     * {@code onColorChangedListener} instance of the onColorChangedListener
     */
    private OnColorChangedListener onColorChangedListener;

    /**
     * {@code onColorSelectedListener} instance of the onColorSelectedListener
     */
    private OnColorSelectedListener onColorSelectedListener;
    private Paint mColorTinyPaint;


    public WheelPicker(Context context) {
        super(context);
        init(null, 0);
    }

    public WheelPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WheelPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * An interface that is called whenever the color is changed. Currently it
     * is always called when the color is changes.
     *
     * @author lars
     */
    public interface OnColorChangedListener {
        public void onColorChanged(int color);
    }

    /**
     * An interface that is called whenever a new color has been selected.
     * Currently it is always called when the color wheel has been released.
     */
    public interface OnColorSelectedListener {
        public void onColorSelected(int color);
    }

    /**
     * Set a onColorChangedListener
     *
     * @param listener {@code OnColorChangedListener}
     */
    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.onColorChangedListener = listener;
    }

    /**
     * Gets the onColorChangedListener
     *
     * @return {@code OnColorChangedListener}
     */
    public OnColorChangedListener getOnColorChangedListener() {
        return this.onColorChangedListener;
    }

    /**
     * Set a onColorSelectedListener
     *
     * @param listener {@code OnColorSelectedListener}
     */
    public void setOnColorSelectedListener(OnColorSelectedListener listener) {
        this.onColorSelectedListener = listener;
    }

    /**
     * Gets the onColorSelectedListener
     *
     * @return {@code OnColorSelectedListener}
     */
    public OnColorSelectedListener getOnColorSelectedListener() {
        return this.onColorSelectedListener;
    }

    /**
     * Color of the latest entry of the onColorChangedListener.
     */
    private int oldChangedListenerColor;

    /**
     * Color of the latest entry of the onColorSelectedListener.
     */
    private int oldSelectedListenerColor;

    private void init(AttributeSet attrs, int defStyle) {
//        vibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);

        final TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.ColorPicker, defStyle, 0);
        final Resources b = getContext().getResources();

        mColorWheelThickness = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_wheel_thickness,
                b.getDimensionPixelSize(R.dimen.color_wheel_thickness));
        mColorWheelRadius = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_wheel_radius,
                b.getDimensionPixelSize(R.dimen.color_wheel_radius));
        mPreferredColorWheelRadius = mColorWheelRadius;
        mColorCenterRadius = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_center_radius,
                b.getDimensionPixelSize(R.dimen.color_center_radius));
        mPreferredColorCenterRadius = mColorCenterRadius;
        mColorCenterHaloRadius = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_center_halo_radius,
                b.getDimensionPixelSize(R.dimen.color_center_halo_radius));
        mPreferredColorCenterHaloRadius = mColorCenterHaloRadius;
        mColorPointerRadius = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_pointer_radius,
                b.getDimensionPixelSize(R.dimen.color_pointer_radius));
        mColorPointerHaloRadius = a.getDimensionPixelSize(
                R.styleable.ColorPicker_color_pointer_halo_radius,
                b.getDimensionPixelSize(R.dimen.color_pointer_halo_radius));

        a.recycle();

        mAngle = (float) (-Math.PI / 2);

        Shader s = new SweepGradient(0, 0, COLORS, null);


        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setShader(s);
        mColorWheelPaint.setStyle(Paint.Style.STROKE);
        mColorWheelPaint.setStrokeWidth(mColorWheelThickness);

        mPointerHaloPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointerHaloPaint.setColor(Color.BLACK);
        mPointerHaloPaint.setAlpha(0x50);

        mPointerColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointerColor.setColor(calculateColor(mAngle));

        mCenterNewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterNewPaint.setColor(calculateColor(mAngle));
        mCenterNewPaint.setStyle(Paint.Style.FILL);

        mColorTinyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterNewPaint.setColor(Color.LTGRAY);
        mColorTinyPaint.setStyle(Paint.Style.STROKE);
        mColorTinyPaint.setStrokeWidth(10);

        mCenterOldPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterOldPaint.setColor(calculateColor(mAngle));
        mCenterOldPaint.setStyle(Paint.Style.FILL);

        mCenterHaloPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterHaloPaint.setColor(Color.BLACK);
        mCenterHaloPaint.setAlpha(0x00);

        mCenterNewColor = calculateColor(mAngle);
        mCenterOldColor = calculateColor(mAngle);
        mShowCenterOldColor = true;
    }


    private boolean wheel_color_flag=Value.Companion.getWheel_color_flag();
    public void set_wheel_color_flag(Boolean flag){
        wheel_color_flag=flag;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mTranslationOffset, mTranslationOffset);
        if (!wheel_color_flag || (mRadialSender2==null)) {
            mColorTinyPaint.setColor(Color.LTGRAY);
        }

            // Draw the new selected color in the center.
            canvas.drawArc(mCenterRectangle, 0, 360, true, mColorTinyPaint);
//                canvas.drawCircle(0,0,mColorWheelRectangle.width(),mColorTinyPaint);


//            Log.d("onDraw","rectF="+mCenterRectangle+", rectF.width="+mCenterRectangle.width()+", color="+mColorTinyPaint.getColor());

/*

                void drawCircle(float cx, float cy, float radius, Paint paint)
                绘制圆形。
                cx和cy是圆心坐标，radius是半径长度。

                void drawArc(RectF oval, float startAngle, float sweepAngle. boolean useCenter, Paint paint)
                绘制圆弧形，也是以矩形的内切椭圆为标准。
                其中，startAngle为起始角度，sweepAngle为弧度大小，
                useCenter为true，则是绘制一个扇行，为false，则只是一段圆弧。（ps：startAngle为0时，是圆形钟表3点钟方向
*/

    }


/*
    @Override
    protected void onDraw(Canvas canvas) {
        // All of our positions are using our internal coordinate system.
        // Instead of translating
        // them we let Canvas do the work for us.
        canvas.translate(mTranslationOffset, mTranslationOffset);

        // Draw the color wheel.
        canvas.drawOval(mColorWheelRectangle, mColorWheelPaint);

        float[] pointerPosition = calculatePointerPosition(mAngle);

        // Draw the pointer's "halo"
        canvas.drawCircle(pointerPosition[0], pointerPosition[1],
                mColorPointerHaloRadius, mPointerHaloPaint);

        // Draw the pointer (the currently selected color) slightly smaller on
        // top.
        canvas.drawCircle(pointerPosition[0], pointerPosition[1],
                mColorPointerRadius, mPointerColor);

        // Draw the halo of the center colors.
        canvas.drawCircle(0, 0, mColorCenterHaloRadius, mCenterHaloPaint);

        if (mShowCenterOldColor) {
            // Draw the old selected color in the center.
            canvas.drawArc(mCenterRectangle, 90, 180, true, mCenterOldPaint);

            // Draw the new selected color in the center.
            canvas.drawArc(mCenterRectangle, 270, 180, true, mCenterNewPaint);
        } else {
            // Draw the new selected color in the center.
            canvas.drawArc(mCenterRectangle, 0, 360, true, mCenterNewPaint);
        }
    }
*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int intrinsicSize = 2 * (mPreferredColorWheelRadius + mColorPointerHaloRadius);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(intrinsicSize, widthSize);
        } else {
            width = intrinsicSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(intrinsicSize, heightSize);
        } else {
            height = intrinsicSize;
        }

        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mTranslationOffset = min * 0.5f;

        // fill the rectangle instances.
        mColorWheelRadius = min / 2 - mColorWheelThickness - mColorPointerHaloRadius;
        mColorWheelRectangle.set(-mColorWheelRadius, -mColorWheelRadius,
                mColorWheelRadius, mColorWheelRadius);

        mColorCenterRadius = (int) ((float) mPreferredColorCenterRadius * ((float) mColorWheelRadius / (float) mPreferredColorWheelRadius));
        mColorCenterHaloRadius = (int) ((float) mPreferredColorCenterHaloRadius * ((float) mColorWheelRadius / (float) mPreferredColorWheelRadius));
        mCenterRectangle.set(-mColorCenterRadius, -mColorCenterRadius,
                mColorCenterRadius, mColorCenterRadius);
    }

    private int ave(int s, int d, float p) {
        return s + Math.round(p * (d - s));
    }


    /**
     * 角度正则化
     * 把+/-pi的弧度映射到min到max的int域
     */
    private int calculateInt(float angle, int min, int max) {
        int i = (int) ((angle + Math.PI) * (max - min) / Math.PI / 2 + min);
        if (i < min) {
            i = min;
        } else if (i > max)
            i = max;
//        Log.i("calculateInt", "input=" + angle + "\toutput=" + i);
        return i;
    }

    /**
     * Calculate the color using the supplied angle.
     *
     * @param angle The selected color's position expressed as angle (in rad).
     * @return The ARGB value of the color on the color wheel at the specified
     * angle.
     */
    private int calculateColor(float angle) {
        float unit = (float) (angle / (2 * Math.PI));
        if (unit < 0) {
            unit += 1;
        }

        if (unit <= 0) {
            mColor = COLORS[0];
            return COLORS[0];
        }
        if (unit >= 1) {
            mColor = COLORS[COLORS.length - 1];
            return COLORS[COLORS.length - 1];
        }

        float p = unit * (COLORS.length - 1);
        int i = (int) p;
        p -= i;

        int c0 = COLORS[i];
        int c1 = COLORS[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);

        mColor = Color.argb(a, r, g, b);
        return Color.argb(a, r, g, b);
    }

    private int calulateNextColor() {
        float[] colors = new float[3];
        Color.colorToHSV(mCenterNewColor, colors);
        Log.i("calulateNextColor", "h=" + colors[0] + ", s=" + colors[1] + ", v=" + colors[2]);

        colors[0] += 30;
        if (colors[0] > 360)
            colors[0] -= 360;
        Log.i("calulateNextColor", "color=" + colors[0]);
        return Color.HSVToColor(colors);
    }


    public int getColor() {
        return mCenterNewColor;
    }

    public void setColor(int color) {
        mAngle = colorToAngle(color);
        mPointerColor.setColor(calculateColor(mAngle));

        setNewCenterColor(color);
    }


    private float colorToAngle(int color) {
        float[] colors = new float[3];
        Color.colorToHSV(color, colors);

        return (float) Math.toRadians(-colors[0]);
    }


    private RadialSender2 mRadialSender2 = null;

    public void setBluetoothRadialSender(RadialSender2 mRadialSender2) {
        this.mRadialSender2 = mRadialSender2;
        if (mRadialSender2 != null)
            setNewCenterColor(Color.GREEN);
        invalidate();
        Log.i("setBluetoothRadialSender", "is_null=" + (this.mRadialSender2 == null));
    }


/*
关于haptic feedback：
wheelpicker的view只处理2种事件：
button 的点击和释放
radial 的点击和释放
是否发生长按事件，由系统判断
是否发生radial的事件，经过了多重过滤：
1. 移动是否发生了角度变化（vew计算
2. 角度变化量是否超出了阈值（sender2计算
是否产生haptic feedback，由view计算（对dial事件返回的事件进行过滤

haptic feedback的模式做了如下筛选适配：

release/tick/move并没有明显触感
virtual key press >
keyboard tape/press >
context click >
long press

因此long press匹配给radial事件
button 适配 virtual key
radial press/release适配keyboard press
radial 事件适配 long press

*/

/*

haptic有三种模式：

    忽略view设置
        v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS,HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
    忽略系统设置
       v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING

*/

    private long haptic_last_time =0;
    private int wheel_haptic_minimum_time_flag =Value.Companion.getWheel_haptic_minimum_time_flag();
    private int haptic_use_flag=Value.Companion.getHaptic_use_flag();
    private int wheel_haptic_skip_count_flag=Value.Companion.getWheel_haptic_skip_count_flag();
    private int wheel_haptic_vibrate_time_flag=Value.Companion.getWheel_haptic_vibrate_time_flag();

    public void set_wheel_haptic_min(int wheel_haptic_minimum_time) {
        this.wheel_haptic_minimum_time_flag = wheel_haptic_minimum_time;
    }

    public void set_haptic_use(int i){
        if(i<0 || i>2){
            Log.e("set_haptic_use_flag()","out of define/"+i);
        }     else
        haptic_use_flag=i;
    }

    public void set_wheel_haptic_vibrate_time(int i){
        wheel_haptic_vibrate_time_flag=i;
    }
    public void  set_wheel_haptic_skip_count(int i){
        if(i<1)
            i=1;
        wheel_haptic_skip_count_flag=i;
    }


    public void config_haptic(
            int haptic_use_flag,
            int wheel_haptic_minimum_time_flag,
            int wheel_haptic_vibrate_time_flag,
            int wheel_haptic_skip_count_flag
    ) {
        Log.w("config_haptic()",
                "haptic_use_flag="+haptic_use_flag+", " +
                        "wheel_haptic_minimum_time_flag="+wheel_haptic_minimum_time_flag+", " +
                        "wheel_haptic_vibrate_time_flag="+wheel_haptic_vibrate_time_flag+", " +
                "wheel_haptic_skip_count_flag="+wheel_haptic_skip_count_flag);
        if(haptic_use_flag<0 || haptic_use_flag>2){
            Log.e("config_haptic()","haptic_use_flag out of define/"+haptic_use_flag);
        }
        else
            this.haptic_use_flag=haptic_use_flag;

        if(wheel_haptic_minimum_time_flag<0 || wheel_haptic_minimum_time_flag>3000){
            Log.e("config_haptic()","wheel_haptic_minimum_time_flag out of define/"+wheel_haptic_minimum_time_flag);
        }else
            this.wheel_haptic_minimum_time_flag=wheel_haptic_minimum_time_flag;

        if(wheel_haptic_vibrate_time_flag<0){
            Log.e("config_haptic()","wheel_haptic_vibrate_time_flag out of define/"+wheel_haptic_vibrate_time_flag);
        }else
            this.wheel_haptic_vibrate_time_flag=wheel_haptic_vibrate_time_flag;

        if(wheel_haptic_skip_count_flag<0 || wheel_haptic_skip_count_flag>3000){
            Log.e("config_haptic()","wheel_haptic_skip_count_flag out of define/"+wheel_haptic_skip_count_flag);
        }else
            this.wheel_haptic_skip_count_flag=wheel_haptic_skip_count_flag;
    }

    private void haptic_radial(boolean feedback) {
        if (feedback) {
            long t = System.currentTimeMillis();
            if (t - haptic_last_time > wheel_haptic_minimum_time_flag) {
//                performHapticFeedback(wheel_haptic_vibrate_time_flag);
                haptic(wheel_haptic_vibrate_time_flag);
                haptic_last_time = t;
            }
        }
    }

    private void haptic(int flag){
        if(haptic_use_flag==1){
            performHapticFeedback(flag,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        }else if(haptic_use_flag==2){

        }else{
            performHapticFeedback(flag);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);

        // Convert coordinates to our internal coordinate system
        float x = event.getX() - mTranslationOffset;
        float y = event.getY() - mTranslationOffset;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (x >= -mColorCenterRadius && x <= mColorCenterRadius
                        && y >= -mColorCenterRadius && y <= mColorCenterRadius
                        && mShowCenterOldColor) {
                    mCenterHaloPaint.setAlpha(0x50);
                    setColor(calulateNextColor());
                    invalidate();
                    haptic(HapticFeedbackConstants.VIRTUAL_KEY);
                    Log.i("TouchEvent.down", "button press");
                    if (mRadialSender2 != null)
                        mRadialSender2.sendKeys(1);

                } else {
                    // 去掉了spacer的判定
                    mUserIsMovingPointer = true;
                    invalidate();
                    haptic(HapticFeedbackConstants.KEYBOARD_PRESS);
                    Log.i("TouchEvent.down", "cycle press");
                    if (mRadialSender2 != null)
                        mRadialSender2.setAngel_0(calculateInt(mAngle, 0, 3600));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mUserIsMovingPointer) {
                    mAngle = (float) Math.atan2(y - mSlopY, x - mSlopX);
                    mPointerColor.setColor(calculateColor(mAngle));

                    setNewCenterColor(mCenterNewColor = calculateColor(mAngle));
                    calculateInt(mAngle, 0, 3600);
                    //	    Log.i("TouchEvent.move","color="+mCenterNewColor+"\tangle="+mAngle);
                    if (mRadialSender2 != null) {
                        //
                        //      触感反馈仍然不佳
                        Log.w("wheel_haptic_minimum_time_flag",
                                "value="+wheel_haptic_minimum_time_flag+":"+(wheel_haptic_minimum_time_flag>0));
                        if(wheel_haptic_minimum_time_flag >0)
                            haptic_radial(mRadialSender2.sendDial(  calculateInt(mAngle, 0, 3600),wheel_haptic_skip_count_flag) );
                        else
                            mRadialSender2.sendDial(calculateInt(mAngle, 0, 3600));
                    }

                    invalidate();
                }
                // If user did not press pointer or center, report event not handled
                else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mUserIsMovingPointer = false;
                mCenterHaloPaint.setAlpha(0x00);

                if (onColorSelectedListener != null && mCenterNewColor != oldSelectedListenerColor) {
                    onColorSelectedListener.onColorSelected(mCenterNewColor);
                    oldSelectedListenerColor = mCenterNewColor;
                }

                if (x >= -mColorCenterRadius && x <= mColorCenterRadius
                        && y >= -mColorCenterRadius && y <= mColorCenterRadius
                        && mShowCenterOldColor) {
                    mCenterHaloPaint.setAlpha(0x50);
//                    setColor(getOldCenterColor());
                    invalidate();
                    if (mRadialSender2 != null)
                        mRadialSender2.sendKeys(2);
                }

                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                if (onColorSelectedListener != null && mCenterNewColor != oldSelectedListenerColor) {
                    onColorSelectedListener.onColorSelected(mCenterNewColor);
                    oldSelectedListenerColor = mCenterNewColor;
                }
                break;
        }
        return true;
    }


    /**
     * Calculate the pointer's coordinates on the color wheel using the supplied
     * angle.
     *
     * @param angle The position of the pointer expressed as angle (in rad).
     * @return The coordinates of the pointer's center in our internal
     * coordinate system.
     */
    private float[] calculatePointerPosition(float angle) {
        float x = (float) (mColorWheelRadius * Math.cos(angle));
        float y = (float) (mColorWheelRadius * Math.sin(angle));

        return new float[]{x, y};
    }


    /**
     * Change the color of the center which indicates the new color.
     *
     * @param color int of the color.
     */
    public void setNewCenterColor(int color) {
        mCenterNewColor = color;
        mCenterNewPaint.setColor(color);
        mColorTinyPaint.setColor(color);
        if (mCenterOldColor == 0) {
            mCenterOldColor = color;
            mCenterOldPaint.setColor(color);
        }
        if (onColorChangedListener != null && color != oldChangedListenerColor) {
            onColorChangedListener.onColorChanged(color);
            oldChangedListenerColor = color;
        }
        invalidate();
    }

    /**
     * Change the color of the center which indicates the old color.
     *
     * @param color int of the color.
     */
    public void setOldCenterColor(int color) {
        mCenterOldColor = color;
        mCenterOldPaint.setColor(color);
        invalidate();
    }

    public int getOldCenterColor() {
        return mCenterOldColor;
    }

    /**
     * Set whether the old color is to be shown in the center or not
     *
     * @param show true if the old color is to be shown, false otherwise
     */
    public void setShowOldCenterColor(boolean show) {
        mShowCenterOldColor = show;
        invalidate();
    }

    public boolean getShowOldCenterColor() {
        return mShowCenterOldColor;
    }



    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle state = new Bundle();
        state.putParcelable(STATE_PARENT, superState);
        state.putFloat(STATE_ANGLE, mAngle);
        state.putInt(STATE_OLD_COLOR, mCenterOldColor);
        state.putBoolean(STATE_SHOW_OLD_COLOR, mShowCenterOldColor);

        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;

        Parcelable superState = savedState.getParcelable(STATE_PARENT);
        super.onRestoreInstanceState(superState);

        mAngle = savedState.getFloat(STATE_ANGLE);
        setOldCenterColor(savedState.getInt(STATE_OLD_COLOR));
        mShowCenterOldColor = savedState.getBoolean(STATE_SHOW_OLD_COLOR);
        int currentColor = calculateColor(mAngle);
        mPointerColor.setColor(currentColor);
        setNewCenterColor(currentColor);
    }

    public void setTouchAnywhereOnColorWheelEnabled(boolean TouchAnywhereOnColorWheelEnabled) {
        mTouchAnywhereOnColorWheelEnabled = TouchAnywhereOnColorWheelEnabled;
    }

    public boolean getTouchAnywhereOnColorWheel() {
        return mTouchAnywhereOnColorWheelEnabled;
    }
}
