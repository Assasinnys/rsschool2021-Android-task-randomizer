package com.rsschool.android2021.ui.extentions

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearError() {
    isErrorEnabled = false
    error = ""
}

fun TextInputLayout.callError(@StringRes errorId: Int) {
    isErrorEnabled = true
    error = context.getString(errorId)
}