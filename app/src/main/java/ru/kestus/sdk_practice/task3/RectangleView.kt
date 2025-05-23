package ru.kestus.sdk_practice.task3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import ru.kestus.sdk_practice.R
import kotlin.random.Random

private const val DEFAULT_FILL_STEP = 10f
private const val DEFAULT_BORDER_WIDTH = 16f
private const val START_FILL_STATE = 0f
private const val MAX_FILL_STATE = 100f
private const val COLOR_RANGE_START = 0
private const val COLOR_RANGE_END = 256

class RectangleView : View {

    private var _borderWidth: Float? = null
        set(value) {
            borderPaint.strokeWidth = value ?: DEFAULT_BORDER_WIDTH
            field = value
        }
    private val borderWidth
        get() = _borderWidth ?: DEFAULT_BORDER_WIDTH
    private var _fillStep: Float? = null
    private val fillStep
        get() = _fillStep ?: DEFAULT_BORDER_WIDTH
    private var currentFillState: Float = START_FILL_STATE

    private val paint = Paint()
    private val borderPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = borderWidth
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet, defStyle: Int) {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.RectangleView, defStyle, 0
        )
        _borderWidth = a.getDimension(
            R.styleable.RectangleView_borderWidth,
            DEFAULT_BORDER_WIDTH
        )
        _fillStep = a.getFloat(
            R.styleable.RectangleView_fillStep,
            DEFAULT_FILL_STEP
        )
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        // draw background
        val fillFraction = currentFillState / MAX_FILL_STATE
        val fillAmount = height * fillFraction
        val fillHeight = height - fillAmount
        canvas.drawRect(
            0f,
            fillHeight,
            width.toFloat(),
            height.toFloat(),
            getPaintRandomColor()
        )
        // draw border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), borderPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val newState = currentFillState + fillStep
        currentFillState =
            if (currentFillState >= MAX_FILL_STATE) 0f
            else newState
        invalidate()
        return super.onTouchEvent(event)
    }

    private fun getPaintRandomColor() = paint.apply {
        color = Color.rgb(
            Random.nextInt(COLOR_RANGE_START, COLOR_RANGE_END),
            Random.nextInt(COLOR_RANGE_START, COLOR_RANGE_END),
            Random.nextInt(COLOR_RANGE_START, COLOR_RANGE_END)
        )
    }
}