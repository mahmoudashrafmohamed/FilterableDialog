package com.github.mahmoud_ashraf.filterabledialog

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addTextChangedListener(listener: (p0: Editable?) -> Unit) {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)  = Unit
        override fun afterTextChanged(p0: Editable?) {
            listener.invoke(p0)
        }
    }
    addTextChangedListener(textWatcher)
}