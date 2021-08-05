package com.educonnect.libraries.interfaces

import android.view.MotionEvent
import android.view.View

interface OutsideTouchListener {
    fun onTouchOutside(view: View?, event: MotionEvent?)
}