package com.github.chartslib

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LinedChart(private val chart: Chart,
                 private val lineWay: LineWay): Chart by chart{

    private val linePaint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 10f
    }


    override fun draw(sizeOfChart: Float, index: Int, item: Int, maxDataVal: Int, padding: Float, canvas: Canvas) {
        lineWay.drawLine(padding, linePaint, canvas)
        chart.draw(sizeOfChart, index, item, maxDataVal, padding, canvas)
    }

    abstract class LineWay(private val lineCount: Int){
        private var drawn = false

        fun drawLine(padding: Float, linePaint: Paint, canvas: Canvas){
            if (!drawn){
                draw(lineCount, padding, linePaint, canvas)
                drawn = true
            }
        }

        protected abstract fun draw(lineCount: Int, padding: Float, linePaint: Paint, canvas: Canvas)
    }

    class VerticalWay(lineCount: Int): LineWay(lineCount){
        override fun draw(lineCount: Int, padding: Float, linePaint: Paint, canvas: Canvas) {
            val linePadding = canvas.width.toFloat() / lineCount
            for (i in 0 until lineCount){
                canvas.drawLine(linePadding * i, 0f, linePadding * i, canvas.height.toFloat(), linePaint)
            }
        }
    }

    class HorizontalWay(lineCount: Int): LineWay(lineCount){
        override fun draw(lineCount: Int, padding: Float, linePaint: Paint, canvas: Canvas) {
            val linePadding = canvas.height.toFloat() / lineCount
            for (i in 0 until lineCount){
                canvas.drawLine(0f, linePadding * i, canvas.width.toFloat(), linePadding * i, linePaint)
            }
        }
    }
}