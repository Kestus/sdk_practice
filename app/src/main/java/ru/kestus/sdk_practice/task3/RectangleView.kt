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


class RectangleView : View {

    private var borderWidth: Float? = null

    private val defStep: Float = 10f
    private var fillStep: Float = defStep

    private var fillState: Float = 0f
    private val paint = Paint()

    private val borderPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = 16f
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
        borderWidth = a.getDimension(
            R.styleable.RectangleView_borderWidth,
            0f
        )
        fillStep = a.getFloat(
            R.styleable.RectangleView_fillStep,
            defStep
        )
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        // draw background
        canvas.drawRect(
            0f,
            height - (height * (fillState / 100)),
            width.toFloat(),
            height.toFloat(),
            getPaintRandomColor()
        )
        // draw border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), borderPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val newState = fillState + fillStep
        fillState =
            if (fillState >= 100f) 0f
            else newState
        invalidate()
        return super.onTouchEvent(event)
    }

    private fun getPaintRandomColor() = paint.apply {
        color = Color.rgb(
            Random.nextInt(0, 256),
            Random.nextInt(0, 256),
            Random.nextInt(0, 256)
        )
    }
}