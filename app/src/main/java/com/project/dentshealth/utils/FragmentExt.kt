package com.project.dentshealth.utils

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

fun BottomSheetDialogFragment.makeSnackbar(text: String, duration: Int): Snackbar? {
    dialog?.window?.decorView?.let {
        return Snackbar.make(it, text, duration)
    }

    return null
}