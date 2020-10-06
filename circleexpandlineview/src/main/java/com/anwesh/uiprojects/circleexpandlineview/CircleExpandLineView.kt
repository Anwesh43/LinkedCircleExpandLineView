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