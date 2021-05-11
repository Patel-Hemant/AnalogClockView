package com.hemantpatel.clock_library

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

public class AnalogClockView : View {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        setupAttributes(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
        setupAttributes(attrs)
    }

    // create handler and time updater Runnable
    private var mHandler: Handler = Handler()
    private lateinit var mTimeUpdater: Runnable

    // custom attribute
    private var mDialColor: Int = Color.GRAY
    private var mPointsColor: Int = Color.WHITE
    private var mHourHandColor: Int = Color.GREEN
    private var mMinuteHandColor: Int = Color.YELLOW
    private var mSecondHandColor: Int = Color.RED
    private var mHourHandWidth: Float = 15f
    private var mMinuteHandWidth: Float = 12f
    private var mSecondHandWidth: Float = 8f


    // paints
    private var mTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var mDialPaint: Paint
    private lateinit var mPointerPaint: Paint

    // initial position of clock
    private var hourData = 0f
    private var minuteData = 0f
    private var secondData = 0

    // custom view or canvas width, height and clock radius
    private var mWidth = 0.0f
    private var mHeight = 0.0f
    private var mRadius = 0.0f

    init {
        // create text paint
        mTextPaint.color = Color.BLACK
        mTextPaint.style = Paint.Style.FILL_AND_STROKE
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.textSize = 40f

        // initialize time updater runnable
        mTimeUpdater = object : Runnable {
            override fun run() {
                // update clock time data
                val currentTime: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                setClockTime(
                    currentTime.substring(0, 2).toFloat() % 12,
                    currentTime.substring(3, 5).toFloat(),
                    currentTime.substring(6, 8).toInt()
                )
                // recall update runnable after 1 sec (1000ms)
                mHandler.postDelayed(this, 1000)
            }
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //create DialPaint
        mDialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDialPaint.color = mDialColor

        //create PointerPaint
        mPointerPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPointerPaint.color = mPointsColor

        // Draw the dial
        canvas?.drawCircle(mWidth / 2, mHeight / 2, mRadius, mDialPaint)

        // Draw the indicator mark.
        val numberCircleRadius = mRadius - 60
        val pointRadius = 20f
        for (i in 0..11) {
            val xyData = calcXYForPosition(i.toFloat(), numberCircleRadius, 30)
            canvas?.drawCircle(xyData[0], xyData[1], pointRadius, mPointerPaint)
        }

        // draw clock hands
        // hour hand
        drawHandWithPaint(
            canvas,
            mHourHandColor,
            mHourHandWidth,
            calcXYForPosition(hourData, numberCircleRadius - 130, 30)
        )
        // minute hand
        drawHandWithPaint(
            canvas,
            mMinuteHandColor,
            mMinuteHandWidth,
            calcXYForPosition(minuteData, numberCircleRadius - 80, 6)
        )
        // second hand
        drawHandWithPaint(
            canvas,
            mSecondHandColor,
            mSecondHandWidth,
            calcXYForPosition(secondData.toFloat(), numberCircleRadius - 30, 6)
        )
    }

    // when view created or device rotate this will called so we can get width and height of canvas
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        mRadius = ((min(mWidth, mHeight)) * (0.8) / 2).toFloat()
    }

    // get x, y point by position and radius
    private fun calcXYForPosition(pos: Float, rad: Float, skipAngle: Int): ArrayList<Float> {
        val result = ArrayList<Float>(2)
        val startAngle = 270f

        // Angle between continually number radians. 360/12 = 30
        val angle = startAngle + (pos * skipAngle)

        /*
         x = r*cos(angle) & y = r*sin(angle)
         position of point p1 is by respect of (x,y) = (x1,y1)
         then position of p1 is by respect of (X,Y) = (x1-X, y1-Y)
         */
        result.add(0, (rad * cos(angle * Math.PI / 180) + width / 2).toFloat())
        result.add(1, (height / 2 + rad * sin(angle * Math.PI / 180)).toFloat())
        return result
    }

    // draw hand
    private fun drawHand(canvas: Canvas?, paint: Paint, xyData: ArrayList<Float>) {
        canvas?.drawLine(mWidth / 2, mHeight / 2, xyData[0], xyData[1], paint)
    }

    // draw hand with pant properties
    private fun drawHandWithPaint(
        canvas: Canvas?,
        handColor: Int,
        strokeWidth: Float,
        xyData: ArrayList<Float>
    ) {
        val handPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        handPaint.color = handColor
        handPaint.strokeWidth = strokeWidth
        canvas?.drawLine(mWidth / 2, mHeight / 2, xyData[0], xyData[1], handPaint)
    }

    // setup custom attribute
    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of attributes
        val typedArray: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.AnalogClockView, 0, 0)

        // Extract custom attributes into member variables
        mDialColor =
            typedArray.getColor(R.styleable.AnalogClockView_dialColor, mDialColor)
        mPointsColor =
            typedArray.getColor(R.styleable.AnalogClockView_pointsColor, mPointsColor)
        mHourHandColor =
            typedArray.getColor(R.styleable.AnalogClockView_hourHandColor, mHourHandColor)
        mMinuteHandColor =
            typedArray.getColor(R.styleable.AnalogClockView_minuteHandColor, mMinuteHandColor)
        mSecondHandColor =
            typedArray.getColor(R.styleable.AnalogClockView_secondHandColor, mSecondHandColor)
        mHourHandWidth =
            typedArray.getFloat(R.styleable.AnalogClockView_hourHandWidth, mHourHandWidth)
        mMinuteHandWidth =
            typedArray.getFloat(R.styleable.AnalogClockView_minuteHandWidth, mMinuteHandWidth)
        mSecondHandWidth =
            typedArray.getFloat(R.styleable.AnalogClockView_secondHandWidth, mSecondHandWidth)

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }


    // set clock hand
    private fun setClockTime(hour: Float, minute: Float, second: Int) {
        hourData = hour + (minute / 60)
        Log.d("time_data", hourData.toString())
        minuteData = minute
        secondData = second
        invalidate()
    }

    // start clock
    fun startClock() {
        mHandler.post(mTimeUpdater)
    }

    // stop clock
    fun stopClock() {
        mHandler.removeCallbacks(mTimeUpdater)
    }

}