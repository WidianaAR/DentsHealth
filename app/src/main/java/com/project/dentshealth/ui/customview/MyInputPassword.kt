package com.project.dentshealth.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.project.dentshealth.R

class MyInputPassword : TextInputEditText {
    private var textLength = 0
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}

            override fun onTextChanged(sequence: CharSequence, start: Int, count: Int, after: Int) {
                textLength = start + after
                error = if (textLength < 6) resources.getString(
                    R.string.error_length,
                    if (hint != null) hint else "Field") else null
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}