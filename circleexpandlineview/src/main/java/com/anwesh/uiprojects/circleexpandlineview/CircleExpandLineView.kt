package com.anwesh.uiprojects.circleexpandlineview

/**
 * Created by anweshmishra on 07/10/20.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Path
import android.graphics.Color

val colors : Array<Int> = arrayOf(
        "#F44336",
        "#4CAF50",
        "#03A9F4",
        "#FFC107",
        "#009688"
).map({Color.parseColor(it)}).toTypedArray()
val parts : Int = 3
val strokeFactor : Float = 90f
val sizeFactor : Float = 8.2f
val scGap : Float = 0.02f / parts
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val rot : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawCircleExpandLine(scale : Float, w : Float, h : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val size : Float = Math.min(w, h) / sizeFactor
    save()
    translate(w / 2, h / 2)
    rotate(rot * sf3)
    for (j in 0..1) {
        save()
        scale(1f - 2 * j, 1f)
        save()
        val path : Path = Path()
        path.addCircle(size / 2, 0f, size / 2, Path.Direction.CW)
        clipPath(path)
        drawRect(RectF(0f, -size / 2, size * sf1, size / 2), paint)
        restore()
        drawLine(size, 0f, (w / 2 - size) * sf2, 0f, paint)
        restore()
    }
    restore()
}

fun Canvas.drawCELNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawCircleExpandLine(scale, w, h, paint)
}